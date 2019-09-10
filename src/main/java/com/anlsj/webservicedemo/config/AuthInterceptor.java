package com.anlsj.webservicedemo.config;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import java.util.List;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private static final String USER_NAME = "ROOT";
    private static final String PASSWORD = "ROOT";

    public AuthInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {

        List<Header> headers = null;

        String userName = null;
        String password = null;

        try {
            headers = message.getHeaders();
            if (headers == null || headers.size() == 0) {
                throw new Fault(new IllegalArgumentException("找不到Header信息，无法验证身份信息！"));
            }
            for (Header header : headers) {
                SoapHeader soapHeader = (SoapHeader) header;
                Element e = (Element) soapHeader.getObject();
                NodeList userNameNode = e.getElementsByTagName("username");
                NodeList passwordNode = e.getElementsByTagName("password");
                userName = userNameNode.item(0).getTextContent();
                password = passwordNode.item(0).getTextContent();
                if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
                    throw new Fault(new IllegalArgumentException("用户信息不能为空！"));
                }
            }
            if (!(userName.equals(USER_NAME) && password.equals(PASSWORD))) {
                SOAPException soapException = new SOAPException("认证失败");
                throw new Fault(soapException);
            }
        } catch (Exception e) {
            throw new Fault(e);
        }
    }
}
