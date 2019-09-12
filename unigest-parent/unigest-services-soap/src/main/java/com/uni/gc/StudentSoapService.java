package com.uni.gc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPException;

@Stateless(name = "StudentSoapService")
@WebService
public class StudentSoapService implements IStudentSoapLocal, IStudentSoapRemote {
	
	@Resource
	WebServiceContext context;

	@Override
	@WebMethod(operationName = "searchStudentById")
	public MessageResponseDTO searchStudentById(@WebParam(name = "studentId") Integer studentId) {
		MessageResponseDTO messageResponse = new MessageResponseDTO();
		
		if (this.isAuthenticated()) {
			StudentDTO studentFound = null;
			StudentDTO studentDeserialized = null;
			ByteArrayOutputStream studentOutputStream = new ByteArrayOutputStream();
			Unmarshaller unmarshaller = null;
			Marshaller marshaller = null;
			
			
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(com.uni.gc.MessageResponseDTO.class);
				
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
			
			messageResponse.setStatusId(200);
			messageResponse.setBodyMessage(studentDeserialized);
			
		} else {
			messageResponse.setStatusId(401);
			messageResponse.setBodyMessage("Unauthorized Access");
		}
		
		return messageResponse;
	}
	
	private boolean isAuthenticated() {
        MessageContext messageContext = context.getMessageContext();
        Map httpHeaders = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userNameList = (List) httpHeaders.get("uname");
        List passwordList = (List) httpHeaders.get("pass");
 
        if (userNameList.contains("saurabh") && passwordList.contains("java"))
            return true;
        else
            return false;
    }

}