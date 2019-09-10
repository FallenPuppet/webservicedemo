package com.anlsj.webservicedemo.services;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;

@WebService(name = "DemoService", targetNamespace = "http://www.anlsj.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DemoService {

    @WebMethod
    String HelloWorld(@WebParam(name = "userName") String userName) throws UnsupportedEncodingException;
}
