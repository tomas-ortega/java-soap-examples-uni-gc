package com.uni.gc;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Stateless(name = "StudentRestService")
@Path("/")
public class StudentRestService implements IStudentRest {

	@Override
	@GET
	@PermitAll
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchStudentById(@QueryParam("id") Integer studentId) {
		StudentDTO studentFound = null;
		Response httpResponse = null;
		
		try {
		
			//Reglas de negocio -> Business Module
			if (studentId.equals(4)) {
				studentFound = new StudentDTO();
				
				studentFound.setId(4);
				studentFound.setName("Pakito");
			}
			
			if (studentFound == null) {
				//404
				httpResponse = Response.status(Response.Status.NOT_FOUND)
									    .entity("")
										.build();
			} else {
				//200
				httpResponse = Response.status(Response.Status.OK)
										.entity(studentFound)
										.build();
			}
			
		} catch(Exception ex) {
			//500
			httpResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return httpResponse;
	}
}