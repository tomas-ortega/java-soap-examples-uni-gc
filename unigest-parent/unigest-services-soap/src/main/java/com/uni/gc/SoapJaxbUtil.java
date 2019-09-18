package com.uni.gc;

import java.io.File;

import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.xml.sax.SAXException;

public class SoapJaxbUtil {
	public static void main(String args[]) {
		SoapJaxbUtil jaxbUtils = new SoapJaxbUtil();
		
		jaxbUtils.runStudentMarShallingUsingXSD();
	}
	
	private void runStudentFromXmlToJavaClass() {
		SoapJaxbUtil jaxbUtils = new SoapJaxbUtil();
		
		String studentInXmlFormat = "/home/patternsdesign/eclipse-workspace/studentInXmlFormat.xml";
		
		jaxbUtils.studentFromXmlToJavaClass(studentInXmlFormat);
	}
	
	private void studentFromXmlToJavaClass(String filePath) {
		 try {

			File file = new File(filePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentDTO.class);

			//Deserialization
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StudentDTO singleStudent = (StudentDTO) jaxbUnmarshaller.unmarshal(file);
			
			System.out.println("Student Id: " + singleStudent.getId());
			System.out.println("Student Name: " + singleStudent.getName());

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
	}
	
	private void runStudentToXmlAndConsoleOutputWithJaxb() {
		SoapJaxbUtil jaxbUtils = new SoapJaxbUtil();
		String studentInXmlFormat = "/home/patternsdesign/eclipse-workspace/studentOutputInXmlFormat.xml";
		
		StudentDTO singleStudent = new StudentDTO();
		singleStudent.setId(3);
		singleStudent.setName("Pakito");
		
		jaxbUtils.studentToXmlAndConsoleOutputWithJaxb(singleStudent, studentInXmlFormat);
	}
	
	
	private void studentToXmlAndConsoleOutputWithJaxb(StudentDTO student, String filePath) {
	    try {

	        File file = new File(filePath);
	        JAXBContext jaxbContext = JAXBContext.newInstance(StudentDTO.class);
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	        // output pretty printed
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        //Serialization to file
	        jaxbMarshaller.marshal(student, file);
	        
	        //Serialization to standard output
	        jaxbMarshaller.marshal(student, System.out);

	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }
	}
	
	private void runClientValidationWithXSD() {
		SoapJaxbUtil jaxbUtils = new SoapJaxbUtil();
		String xsdPath = "/home/patternsdesign/eclipse-workspace/student_validation.xsd";
		
		StudentDTO singleStudent = new StudentDTO();
		singleStudent.setId(3);
		singleStudent.setName("Pako");
		
		jaxbUtils.studentValidationWithXSD(singleStudent, xsdPath);
	}
	
	private void studentValidationWithXSD(StudentDTO singleStudent, String xsdPath) {
		try {
			
			/* 
			 * Load Schema XSD Validator
			 **/
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(xsdPath));
			
		  /* 
		   * Context is created and used to create sources for single client
		   **/
		  JAXBContext jaxbContext = JAXBContext.newInstance(StudentDTO.class);
		  JAXBSource jaxbSourceClient = new JAXBSource(jaxbContext, singleStudent);
		  
		  
		  /*
		   * Validator initialized
		   **/
		  Validator clientValidator = schema.newValidator();
		  
		  try {
			  clientValidator.validate(jaxbSourceClient);
			  System.out.println("Client Data Validation Ok!!! - Ready To Send!!!");
			  
		  } catch(SAXException ex) {
			  ex.printStackTrace();
		  }
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void runStudentMarShallingUsingXSD() {
		SoapJaxbUtil jaxbUtils = new SoapJaxbUtil();
		String xsdPath = "/home/patternsdesign/eclipse-workspace/student_validation.xsd";
		
		StudentDTO singleStudent = new StudentDTO();
		singleStudent.setId(3);
		singleStudent.setName("Pako");
		
		jaxbUtils.studentMarShallingUsingXSD(singleStudent, xsdPath);
	}
	
	private void studentMarShallingUsingXSD(StudentDTO singleStudent, String xsdPath) {
		try {
			/* 
			 * Load Schema XSD Validator
			 **/
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(xsdPath));
			
			/* 
			 * Context is created and used to create sources for single client
			 **/
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentDTO.class);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
			marshaller.setSchema( schema );
			
			/** XD Schema use a validation handler for validate the objects
			 **/
			
			marshaller.setEventHandler( new MyValidationEventHandler() );
			try
			{
			    marshaller.marshal(singleStudent, System.out);
			}
			catch( JAXBException ex )
			{
			    ex.printStackTrace();
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
		
		private class MyValidationEventHandler implements ValidationEventHandler {

			@Override
			public boolean handleEvent(ValidationEvent event) {
				System.out.println( "Error catched!!" );
		        System.out.println( "event.getSeverity():  " + event.getSeverity() );
		        System.out.println( "event:  " + event.getMessage() );
		        System.out.println( "event.getLinkedException():  " + event.getLinkedException() );
		        //the locator contains much more information like line, column, etc.
		        System.out.println( "event.getLocator().getObject():  " + event.getLocator().getObject() );
		        return false;
			}
		}
}
