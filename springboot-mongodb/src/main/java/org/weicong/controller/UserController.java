package org.weicong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weicong.entity.User;
import org.weicong.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "测试用户管理" ,tags = "测试用户管理")
@RequestMapping("user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "保存用户信息", notes = "保存用户信息")
	@PostMapping
	public String save(@RequestBody User user) {
		// 检验略
		userService.save(user);
		return "success";
	}
	
	@ApiOperation(value = "获取用户信息", notes = "获取用户信息")
	@GetMapping("{username}")
	public User getByUsername(@PathVariable String username) {
		// 检验略
		return userService.getByUsername(username);
	}
	
	@ApiOperation(value = "删除用户信息", notes = "删除用户信息")
	@DeleteMapping("{id}")
	public String remove(@PathVariable String id) {
		// 检验略
		int result = userService.remove(Integer.valueOf(id).longValue());
		if (result != 0) {
			return "success";
		}
		return "fail";
	}

}
