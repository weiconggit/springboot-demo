package org.weicong.kafka;

/**
 * @author weicong
 * @time   2020年12月30日
 * @version 1.0
 */
public class MainTest {
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.security.auth.login.config"));
		System.setProperty("java.security.auth.login.config", "haha");
		System.out.println(System.getProperty("java.security.auth.login.config"));
		System.setProperty("java.security.auth.login.config", "kak45a");
	}


}
