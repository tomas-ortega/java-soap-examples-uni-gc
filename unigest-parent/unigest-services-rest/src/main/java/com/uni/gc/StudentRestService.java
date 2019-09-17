package com.uni.gc;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.uni.gc.ejb.IStudentLogic;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Stateless(name = "StudentRestService")
@Path("/")
public class StudentRestService implements IStudentRest {
	
	private IStudentLogic studentLogic;
	
	@Inject
	public void setStudentLogic(IStudentLogic studentLogic) {
		this.studentLogic = studentLogic;
	}
	
	@GET
	@DenyAll
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello() {
		Response httpResponse = null;
		
		return httpResponse;
	}
	

	@Override
	@GET
	@PermitAll
	@Path("/client")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchStudentById(@QueryParam("id") Integer studentId) {
		StudentDTO studentFound = null;
		Response httpResponse = null;
		
	
		try {
			studentFound = this.studentLogic.searchStudentById(studentId);
			
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