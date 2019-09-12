package com.uni.gc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "MessageResponseDTO")
@XmlSeeAlso({StudentDTO.class})
public class MessageResponseDTO implements Serializable {
	private static final long serialVersionUID = 8775655240518113450L;
	
	@XmlElement(name = "statusId")
	private Integer statusId;
	
	@XmlElement(name = "bodyMessage")
	private Object bodyMessage;
	
	public MessageResponseDTO() {
		
	}
	
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
	@XmlTransient
	public Integer getStatusId() {
		return this.statusId;
	}
	
	
	public void setBodyMessage(Object bodyMessage) {
		this.bodyMessage = bodyMessage;
	}
	
	@XmlTransient
	public Object getBodyMessage() {
		return this.bodyMessage;
	}
}
