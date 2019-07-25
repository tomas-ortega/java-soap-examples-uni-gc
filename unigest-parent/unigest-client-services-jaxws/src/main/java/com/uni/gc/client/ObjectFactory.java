
package com.uni.gc.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.uni.gc.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SearchStudentById_QNAME = new QName("http://gc.uni.com/", "searchStudentById");
    private final static QName _SearchStudentByIdResponse_QNAME = new QName("http://gc.uni.com/", "searchStudentByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.uni.gc.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchStudentByIdResponse }
     * 
     */
    public SearchStudentByIdResponse createSearchStudentByIdResponse() {
        return new SearchStudentByIdResponse();
    }

    /**
     * Create an instance of {@link SearchStudentById }
     * 
     */
    public SearchStudentById createSearchStudentById() {
        return new SearchStudentById();
    }

    /**
     * Create an instance of {@link StudentDTO }
     * 
     */
    public StudentDTO createStudentDTO() {
        return new StudentDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchStudentById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gc.uni.com/", name = "searchStudentById")
    public JAXBElement<SearchStudentById> createSearchStudentById(SearchStudentById value) {
        return new JAXBElement<SearchStudentById>(_SearchStudentById_QNAME, SearchStudentById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchStudentByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gc.uni.com/", name = "searchStudentByIdResponse")
    public JAXBElement<SearchStudentByIdResponse> createSearchStudentByIdResponse(SearchStudentByIdResponse value) {
        return new JAXBElement<SearchStudentByIdResponse>(_SearchStudentByIdResponse_QNAME, SearchStudentByIdResponse.class, null, value);
    }

}
