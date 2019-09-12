package com.uni.gc;

import javax.ejb.Stateless;

import com.uni.gc.ejb.IStudentLogic;

@Stateless(name="StudentEJB")
public class StudentEJB implements IStudentLogic {

	@Override
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
	}
}
