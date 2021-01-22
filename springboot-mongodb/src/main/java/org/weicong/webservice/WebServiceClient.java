package org.weicong.webservice;

import java.nio.charset.Charset;

//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.springframework.http.HttpEntity;tpEntity;

/**
 * @author weicong
 * @time   2021年1月13日
 * @version 1.0
 */
public class WebServiceClient {
	
	public static void main(String[] args) {
        String wsdl = "http://localhost:9000/webs?wsdl";                                             
        int timeout = 10000;                                                                               
        StringBuffer sb = new StringBuffer("");                                                            
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");                                           
        sb.append("<soap:Envelope "                                                                        
                + "xmlns:api='http://webservice.weicong.org/' "                                                       
                + "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "                                 
                + "xmlns:xsd='http://www.w3.org/2001/XMLSchema' "                                          
                + "xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>");                              
        sb.append("<soap:Body>");                                                                          
        sb.append("<api:sayHello>");                                                                       
        sb.append("<arg0>消息1</arg0>");                                                                      
        sb.append("</api:sayHello>");                                                                      
        sb.append("</soap:Body>");                                                                         
        sb.append("</soap:Envelope>");  

	}

//	public static String doPostSoap1_1(String postUrl, String soapXml,
//			String soapAction) {
//		String retStr = "";
//		// 创建HttpClientBuilder
//		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//		// HttpClient
//		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
//		HttpPost httpPost = new HttpPost(postUrl);
//		try {
//			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
//			httpPost.setHeader("SOAPAction", soapAction);
//			StringEntity data = new StringEntity(soapXml,
//					Charset.forName("UTF-8"));
//			httpPost.setEntity(data);
//			CloseableHttpResponse response = closeableHttpClient
//					.execute(httpPost);
//			HttpEntity httpEntity = response.getEntity();
//			if (httpEntity != null) {
//				// 打印响应内容
//				retStr = EntityUtils.toString(httpEntity, "UTF-8");
//			}
//                response.close();
//			// 释放资源
//			closeableHttpClient.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return retStr;
//	}
}
