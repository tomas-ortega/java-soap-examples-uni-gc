package com.uni.gc;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.uni.gc.ejb.IUserLogic;

@Stateless(name = "LoginRestService")
@Path("/")
public class LoginRestService implements ILoginRest {
	
	private IUserLogic userLogic;

	@Inject
	public void setUserLogic(IUserLogic userLogic) {
		this.userLogic = userLogic;
	}
	
	@POST
	@PermitAll
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginInfoDTO loginInfo) {
		UserDTO userFound = null;
		Response httpResponse = null;
		
		try {
			if (loginInfo != null) {
				userFound = this.userLogic.searchUser(loginInfo.getEmail());
				
				if (userFound == null) {
					//404
					httpResponse = Response.status(Response.Status.NOT_FOUND)
										   .build();
				} else {
					//200
					httpResponse = Response.status(Response.Status.OK)
										   .entity(userFound)
										   .build();
				}
			}
		} catch(Exception ex) {
			//500
			httpResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return httpResponse;
	}
}
