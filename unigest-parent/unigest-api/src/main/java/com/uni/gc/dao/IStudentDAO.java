package com.uni.gc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.uni.gc.StudentDTO;

public interface IStudentDAO {
	public StudentDTO searchStudentById(Connection connectionReference, Integer studentId) throws SQLException;
}
