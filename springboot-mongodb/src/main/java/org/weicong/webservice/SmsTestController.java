package org.weicong.webservice;

import javax.xml.ws.Endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weicong
 * @time   2021年1月22日
 * @version 1.0
 */
@RestController
public class SmsTestController {

	private String sign;
	
	@GetMapping(value = "setsign/{sign}")
	public String setsign(@PathVariable(value = "sign") String sign) {
		this.sign = sign;
		return "sign is: " + sign;
	}
	
	// ~ HTTP 短信测试接口
	// ==========================================
	
	@GetMapping("sms")
	public String haha(@RequestParam String phone) {
		System.out.println(phone);
		return "sms phone is: " + phone + ", sign is: " + this.sign;
	}
	
	@PutMapping("sms")
	public String haha2(@RequestParam String phone) {
		System.out.println(phone);
		return "sms phone is: " + phone + ", sign is: " + this.sign;
	}
	
	@PostMapping("sms")
	public String haha3(@RequestParam String phone) {
		System.out.println(phone);
		return "sms phone is: " + phone + ", sign is: " + this.sign;
	}
	
	// ~ WEBSERVICE
	// ===========================================
	
	@GetMapping("startws")
	public String startws() {
		new Thread(() -> {
			System.out.println("wb server is running");
	        String address="http://localhost:9002/webs";
	        Object implementor =new WebServiceTest();
	        Endpoint.publish(address, implementor);
		}).start();
		return "start success";
	}
}
