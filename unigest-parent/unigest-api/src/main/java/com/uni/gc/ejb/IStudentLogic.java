package com.uni.gc.ejb;

import com.uni.gc.StudentDTO;

public interface IStudentLogic {
	public StudentDTO searchStudentById(Integer studentId) throws Exception;
}
