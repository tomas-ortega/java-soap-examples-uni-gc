package com.uni.gc;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "StudentForDatabindingDTO")
public class StudentForDatabindingDTO implements Serializable {

	private static final long serialVersionUID = 8929618698995393447L;
	
	@XmlElement(name = "id")
	private Integer studentId;
	
	@XmlElement(name = "name")
	private String studentName;
	
	@XmlElement(name = "registerDate")
	private LocalDate registerDate;
	
	public StudentForDatabindingDTO() {
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
	
	@XmlTransient
	public LocalDate getRegisterDate() {
		return this.registerDate;
	}
	
	public void setRegisterDate(LocalDate currentRegisterDate) {
		this.registerDate = currentRegisterDate;
	}
}
