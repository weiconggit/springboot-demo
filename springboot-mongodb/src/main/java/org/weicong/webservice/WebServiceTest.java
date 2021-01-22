package org.weicong.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author weicong
 * @time   2021年1月13日
 * @version 1.0
 */
@WebService
public class WebServiceTest {

    @WebMethod
    public String sayHello(String str){
        String result = "Hello World, "+str;
        System.err.println(result);
        return result;
    }
    public static void main(String[] args) {
        System.out.println("server is running");
        String address="http://localhost:9000/webs";
        Object implementor =new WebServiceTest();
        Endpoint.publish(address, implementor);
    }

}
