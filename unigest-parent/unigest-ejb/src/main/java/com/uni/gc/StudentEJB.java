package com.uni.gc;

import java.sql.Connection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.uni.gc.dao.IStudentDAO;
import com.uni.gc.ejb.IStudentLogic;

@Stateless(name="StudentEJB")
public class StudentEJB implements IStudentLogic {
	
	private IStudentDAO studentDAO;
	
	@Inject
	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@Override
	public StudentDTO searchStudentById(Integer studentId) throws Exception {
		Connection connectionReference = null;
		StudentDTO studentFound = null;
		
		try {
			connectionReference = ConnectionDB.getConnection("OracleDS");
			
			studentFound = this.studentDAO.searchStudentById(connectionReference, studentId);
			
			return studentFound;
		} catch(Exception ex) {
			throw ex;
		} finally {
			ConnectionDB.closeConnection(connectionReference);
		}
	}

	/*@Override
	public StudentDTO searchStudentById(Integer studentId) throws Exception {
		try {
			StudentDTO studentFound = null;
						
			if (studentId.equals(4)) {
				studentFound = new StudentDTO();
				
				studentFound.setId(4);
				studentFound.setName("Pakito");
			}
			
			return studentFound;
		} catch(Exception ex) {
			throw ex;
		}
	}*/
}
