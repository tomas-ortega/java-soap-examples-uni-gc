package com.uni.gc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Stateless(name = "StudentSoapService")
@WebService
public class StudentSoapService implements IStudentSoapLocal, IStudentSoapRemote {

	@Override
	@WebMethod(operationName = "searchStudentById")
	public StudentDTO searchStudentById(@WebParam(name = "studentId") Integer studentId) {
		StudentDTO studentFound = null;
		StudentDTO studentDeserialized = null;
		ByteArrayOutputStream studentOutputStream = new ByteArrayOutputStream();
		Unmarshaller unmarshaller = null;
		Marshaller marshaller = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentDTO.class);
			
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			if (studentId.equals(4)) {
				studentFound = new StudentDTO();
				
				studentFound.setId(4);
				studentFound.setName("Pakito");
			}
			
			//Serialización
			//Convert Objecto StudentDTO -> array binario
			marshaller.marshal(studentFound, new OutputStreamWriter(studentOutputStream));
		
			
			//Deserialización
			unmarshaller = jaxbContext.createUnmarshaller();
			studentDeserialized = (StudentDTO) unmarshaller.unmarshal(new ByteArrayInputStream(studentOutputStream.toByteArray()));
			
		} catch(JAXBException ex) {
			ex.printStackTrace();
		}
		
		return studentDeserialized;
	}

}