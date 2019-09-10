package com.anlsj.webservicedemo.services.impl;

import com.anlsj.webservicedemo.services.DemoService;

import javax.jws.WebService;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@WebService(name = "DemoService", targetNamespace = "http://www.anlsj.com", endpointInterface = "com.anlsj.webservicedemo.services.DemoService")

public class DemoServiceImpl implements DemoService {
    @Override
    public String HelloWorld(String userName) throws UnsupportedEncodingException {

        if (userName.isEmpty()) {
            return "请填写用户名";
        }

        return userName + "，你好世界，当前时间：" + new Date();
    }
}
