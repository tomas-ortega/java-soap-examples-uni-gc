
package com.uni.gc.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "StudentSoapServiceService", targetNamespace = "http://gc.uni.com/", wsdlLocation = "http://patterndesignmaster.local:8080/unigest-services-soap-1.0-SNAPSHOT/StudentSoapService?wsdl")
public class StudentSoapServiceService
    extends Service
{

    private final static URL STUDENTSOAPSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException STUDENTSOAPSERVICESERVICE_EXCEPTION;
    private final static QName STUDENTSOAPSERVICESERVICE_QNAME = new QName("http://gc.uni.com/", "StudentSoapServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://patterndesignmaster.local:8080/unigest-services-soap-1.0-SNAPSHOT/StudentSoapService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        STUDENTSOAPSERVICESERVICE_WSDL_LOCATION = url;
        STUDENTSOAPSERVICESERVICE_EXCEPTION = e;
    }

    public StudentSoapServiceService() {
        super(__getWsdlLocation(), STUDENTSOAPSERVICESERVICE_QNAME);
    }

    public StudentSoapServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), STUDENTSOAPSERVICESERVICE_QNAME, features);
    }

    public StudentSoapServiceService(URL wsdlLocation) {
        super(wsdlLocation, STUDENTSOAPSERVICESERVICE_QNAME);
    }

    public StudentSoapServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, STUDENTSOAPSERVICESERVICE_QNAME, features);
    }

    public StudentSoapServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StudentSoapServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns StudentSoapService
     */
    @WebEndpoint(name = "StudentSoapServicePort")
    public StudentSoapService getStudentSoapServicePort() {
        return super.getPort(new QName("http://gc.uni.com/", "StudentSoapServicePort"), StudentSoapService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StudentSoapService
     */
    @WebEndpoint(name = "StudentSoapServicePort")
    public StudentSoapService getStudentSoapServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://gc.uni.com/", "StudentSoapServicePort"), StudentSoapService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (STUDENTSOAPSERVICESERVICE_EXCEPTION!= null) {
            throw STUDENTSOAPSERVICESERVICE_EXCEPTION;
        }
        return STUDENTSOAPSERVICESERVICE_WSDL_LOCATION;
    }

}
