package com.uni.gc.configuration;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;

import com.uni.gc.ejb.ITokenLogic;

@Provider
public class SecurityInterceptorRequest implements javax.ws.rs.container.ContainerRequestFilter {
	
	private ITokenLogic iTokenLogic;
	
	@Inject
	public void setTokenLogin(ITokenLogic tokenLogic) {
		this.iTokenLogic = tokenLogic;
	}

   
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
	    ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
	    Method method = methodInvoker.getMethod();
	    
	    //methodInvoker.getMethod().getName()
	   
	    if (method.isAnnotationPresent(PermitAll.class)) {
	       //Login Endpoint
	    	this.validateLogin(requestContext);
	    } else if (method.isAnnotationPresent(DenyAll.class)) {
	        requestContext.abortWith(ACCESS_FORBIDDEN);
	        return;
	    } else {
	       //Common other endpoints
	    	this.validateAccessLogicEndpoints(requestContext);
	    }
	}
	
	private void validateLogin(ContainerRequestContext requestContext) {
		String userNameHeaderValue = requestContext.getHeaderString("username");
		String passwordNameHeaderValue = requestContext.getHeaderString("password");
		
		if (userNameHeaderValue != null
				&& passwordNameHeaderValue != null) {
			if (!userNameHeaderValue.equals("Pakito") || !passwordNameHeaderValue.equals("57925234")) {
				requestContext.abortWith(ACCESS_FORBIDDEN);
		        return;
			} else {
				String generatedToken = this.iTokenLogic.generateToken();
				requestContext.getHeaders().add("Bearer", generatedToken);
			}
		}
	}
	
	private void validateAccessLogicEndpoints(ContainerRequestContext requestContext) {
		try {
			boolean validationResult;
			String receivedTokenInHeader = requestContext.getHeaderString("Bearer");
			
			if (receivedTokenInHeader != null) {
				validationResult = this.iTokenLogic.verifyToken(receivedTokenInHeader);
				
				if (!validationResult) {
					requestContext.abortWith(ACCESS_FORBIDDEN);
			        return;
				}
			} else {
				requestContext.abortWith(ACCESS_FORBIDDEN);
		        return;
			}
		} catch(Exception ex) {
			requestContext.abortWith(ACCESS_FORBIDDEN);
	        return;
		}
	}
}
