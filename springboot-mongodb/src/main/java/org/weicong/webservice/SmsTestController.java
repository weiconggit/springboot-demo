package org.weicong.webservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weicong
 * @time   2021年1月22日
 * @version 1.0
 */
@RestController
@RequestMapping
public class SmsTestController {

	@GetMapping("sms")
	public String haha(@RequestParam String phone) {
		System.err.println(phone);
		return "sms phone is: " + phone;
	}
	@PutMapping("sms")
	public String haha2(@RequestParam String phone) {
		System.err.println(phone);
		return "sms phone is: " + phone;
	}
	@PostMapping("sms")
	public String haha3(@RequestParam String phone) {
		System.err.println(phone);
		return "sms phone is: " + phone;
	}
}
