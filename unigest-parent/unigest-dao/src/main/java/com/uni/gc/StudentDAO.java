package com.uni.gc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;

import com.uni.gc.dao.IStudentDAO;

@Stateless(name="StudentDAO")
public class StudentDAO implements IStudentDAO {

	@Override
	public StudentDTO searchStudentById(Connection connectionReference, Integer studentId) throws SQLException {
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		StudentDTO studentFound = null;
		StringBuilder sql = null;
		
		try {
			sql = new StringBuilder();
			
			sql.append("SELECT ");
			sql.append("student.id,");
			sql.append("student.name");
			
			sql.append(" FROM ");
			sql.append("student");
			
			sql.append(" WHERE ");
			sql.append("student.id = ?");
			
			//PreparedStatement area
			preparedStatement = connectionReference.prepareStatement(sql.toString());
			
			preparedStatement.setInt(1, studentId);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				studentFound = new StudentDTO();
				
				studentFound.setId(resultSet.getInt("id"));
				studentFound.setName(resultSet.getString("name"));
			}
			
			return studentFound;
			
		} catch(SQLException ex) {
			throw ex;
		}
	}
}
