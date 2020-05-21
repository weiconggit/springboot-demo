package org.weicong.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.weicong.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTester {

	@Autowired
	private UserService userService;

	// ~ 基本CURD
	// ==============================================================
	
	@Test
	public void save() {
		User user = new User();
		user.setId(2L);
		user.setUsername("小明");
		user.setPassword("123456");
		userService.save(user);
	}

	@Test
	public void getByUsername() {
		User user = userService.getByUsername("小明");
		System.out.println("user is " + user);
	}
	
	@Test
	public void getById() {
		User user = userService.getById(2L);
		System.out.println(user);
	}
	
	@Test
	public void list() {
		List<User> list = userService.list();
		list.forEach(System.out::println);
	}

	@Test
	public void updateUser() {
		User user = new User();
		user.setId(2L);
		user.setUsername("天空");
		user.setPassword("123456");
		userService.update(user);
	}

	@Test
	public void remove() {
		int result = userService.remove(2L);
		System.out.println("remove result is " + result);
	}
	
	// ~ 复杂操作
	// ==============================================================
	
	@Test
	public void listByCriteria() {
		List<User> list = userService.listByCriteria(new User(null, "test", ""));
		System.out.println("===================");
		list.forEach(System.out::println);
	}
	
	@Test
	public void listByFuzzy() {
		List<User> list = userService.listByFuzzy("天", "");
		System.out.println("===================");
		list.forEach(System.out::println);
	}
	
	
	// ~ 多表联查
	// ==============================================================

}
