package com.uni.gc.client.axis;

import com.uni.gc.client.axis.StudentSoapServiceServiceStub.SearchStudentById;
import com.uni.gc.client.axis.StudentSoapServiceServiceStub.SearchStudentByIdResponseE;
import com.uni.gc.client.axis.StudentSoapServiceServiceStub.StudentDTO;

public class MainAxis2 {
	public static void main(String[] args) throws Exception {
		StudentSoapServiceServiceStub axisClientCall = new StudentSoapServiceServiceStub();
		
		StudentSoapServiceServiceStub.SearchStudentByIdE request =
				new StudentSoapServiceServiceStub.SearchStudentByIdE();
		
		SearchStudentById param = new SearchStudentById();
		param.setStudentId(4);
		
		request.setSearchStudentById(param);
		
		SearchStudentByIdResponseE response =
				axisClientCall.searchStudentById(request);
		
		StudentDTO studentFound = null;
		
		studentFound = response.getSearchStudentByIdResponse().get_return();
		
		System.out.println("ID: " + studentFound.getId() + " - " + "Name: " + studentFound.getName());
	}
}
