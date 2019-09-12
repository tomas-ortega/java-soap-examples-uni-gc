package com.uni.gc.configuration;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.ext.Provider;

import com.uni.gc.ejb.ITokenLogic;

@Provider
public class SecurityInterceptorResponse implements javax.ws.rs.container.ContainerResponseFilter {
	
	private ITokenLogic iTokenLogic;
	
	@Inject
	public void setTokenLogin(ITokenLogic tokenLogic) {
		this.iTokenLogic = tokenLogic;
	}

	@Override
	public void filter(ContainerRequestContext requestContext, 
					   ContainerResponseContext responseContext) throws IOException {
		String requestHeaderBearer = requestContext.getHeaderString("Bearer");
		
		if (requestHeaderBearer != null) {
			String generatedToken = this.iTokenLogic.generateToken();
			
			responseContext.getHeaders().putSingle("Bearer", generatedToken);
		}
	}
}
