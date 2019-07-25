package com.uni.gc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "StudentDTO")
public class StudentDTO implements Serializable {

	private static final long serialVersionUID = 9045436552398166916L;
	
	@XmlElement(name = "id")
	private Integer studentId;
	
	@XmlElement(name = "name")
	private String studentName;
	
	public StudentDTO() {
		this.studentId = null;
		this.studentName = null;
	}
	
	public void setId(Integer newId) {
		this.studentId = newId;
	}
	
	@XmlTransient
	public Integer getId() {
		return this.studentId;
	}
	
	public void setName(String newName) {
		this.studentName = newName;
	}
	
	@XmlTransient
	public String getName() {
		return this.studentName;
	}
}