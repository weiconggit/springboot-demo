package org.weicong.service;

import java.util.List;

import org.weicong.entity.User;

public interface UserService {
	
	// ~ 基本CURD
	// ==============================================================
	
	int save(User user);
	
	int remove(long id);
	
	User getByUsername(String username);
	
	User getById(long id);
	
	List<User> list();
	
	int update(User user);
	
	// ~ 复杂操作
	// ==============================================================
	
	List<User> listByCriteria(User user);
	
	List<User> listByFuzzy(String startWith, String endWith);
	
	// ~ 多表联查
	// ==============================================================
	
}
