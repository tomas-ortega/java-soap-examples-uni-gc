package com.uni.gc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.uni.gc.databinding.IStudentForDataBindingSoapLocal;
import com.uni.gc.databinding.IStudentForDataBindingSoapRemote;

@Stateless(name = "StudentSoapDatabindingService")
@WebService()
public class StudentSoapDatabindingService implements IStudentForDataBindingSoapLocal, IStudentForDataBindingSoapRemote {

	@Override
	@WebMethod(operationName = "searchStudentById")
	public StudentForDatabindingDTO searchStudentById(@WebParam(name = "id") Integer studentId,
													  @WebParam(name="name") String name,
													  @WebParam(name="registerDate") String registerDate) {
		StudentForDatabindingDTO myNewStudent = new StudentForDatabindingDTO();
		//StudentForDatabindingDTO studentToReturn = null;
		
		myNewStudent.setId(studentId);
		myNewStudent.setName(name);
		myNewStudent.setRegisterDate(LocalDate.parse(registerDate));
		
		
		//ByteArrayOutputStream clientOutputStream = new ByteArrayOutputStream();
		//Unmarshaller unmarshaller = null;
		
		//try {
			//JAXBContext jaxbContext = JAXBContext.newInstance(StudentForDatabindingDTO.class);
			
			//Marshaller marshaller = jaxbContext.createMarshaller();
			
			//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//marshaller.marshal(myNewStudent, new OutputStreamWriter(clientOutputStream));
			
			//Deserialization
			//unmarshaller = jaxbContext.createUnmarshaller();
			//studentToReturn = (StudentForDatabindingDTO) unmarshaller.unmarshal(new ByteArrayInputStream(clientOutputStream.toByteArray()));
			
		//} catch(JAXBException ex) {
		//	ex.printStackTrace();
		//}
		
		return myNewStudent;
	}
}