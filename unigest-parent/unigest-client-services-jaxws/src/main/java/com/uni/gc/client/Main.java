package com.uni.gc.client;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Main {
	public static void main(String[] args) {
		com.uni.gc.client.StudentDTO studentFound = null;
		com.uni.gc.StudentDTO studentToCheck = null;
		StudentSoapServiceService proxyService = null;
		StudentSoapService soapServiceInterface = null;
		
		proxyService = new StudentSoapServiceService();
		
		soapServiceInterface = proxyService.getStudentSoapServicePort();
		
		studentFound = soapServiceInterface.searchStudentById(new Integer(4));
		
		/** Load data into my StudentDTO domain to check XSD data validation
		 **/
		studentToCheck = new com.uni.gc.StudentDTO();
		studentToCheck.setId(studentFound.getId());
		studentToCheck.setName(studentFound.getName());
		
		if (studentFound != null) {
			studentDataValidator(studentToCheck);
		
			System.out.println(studentToCheck.getId() + " - " + studentToCheck.getName());
		} else {
			System.out.println("STUDENT NULL!!!");
		}
		
	}
	
	public static void studentDataValidator(com.uni.gc.StudentDTO studentToCheck) {
		try {
			
			/** Load Schema Validator XSD
			 **/
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema xsdSchema = 
					schemaFactory.newSchema(new File("/home/patternsdesign/eclipse-workspace/unigest-parent/unigest-client-services-jaxws/src/main/java/com/uni/gc/client/student_validation.xsd"));
			
			/** Create JAXB Context to validate student instance
			 **/
			JAXBContext jaxbContext = JAXBContext.newInstance(com.uni.gc.StudentDTO.class);
			JAXBSource jaxbSourceStudent = new JAXBSource(jaxbContext, studentToCheck);
			
			/** Validator process
			 **/
			Validator studentValidator = xsdSchema.newValidator();
			
			try {
				studentValidator.validate(jaxbSourceStudent);
			} catch(SAXException ex) {
				ex.printStackTrace();
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
