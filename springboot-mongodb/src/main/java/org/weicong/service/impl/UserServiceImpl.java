package org.weicong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.weicong.entity.User;
import org.weicong.service.UserService;

import com.mongodb.WriteResult;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private MongoTemplate mongoTemplate;

	// ~ 基本CURD
	// ==============================================================
	
	public int save(User user) {
		mongoTemplate.save(user);
		return 1;
	}

	public int remove(long id) {
		Query query = new Query(Criteria.where("id").is(id));
		WriteResult result = mongoTemplate.remove(query, User.class);
		if(result != null) {
            return result.getN();
		}
        return 0;
	}

	public User getByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		User user = mongoTemplate.findOne(query, User.class);
		return  user;
	}

	public User getById(long id) {
		return mongoTemplate.findById(id, User.class);
	}

	public List<User> list() {
		return mongoTemplate.findAll(User.class);
	}

	public int update(User user) {
		Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("username", user.getUsername()).set("password", user.getPassword());
        //更新查询返回结果集的第一条
        WriteResult result = mongoTemplate.updateFirst(query,update, User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result != null) {
            return result.getN();
        }
        return 0;
	}
	
	// ~ 复杂操作
	// ==============================================================
	
	/**
	 * 多添建查询
	 * @param user
	 * @return
	 */
	public List<User> listByCriteria(User user){
		
		if (Objects.isNull(user)) {
			return new ArrayList<>();
		}
		
		Query query = new Query();
		
		if (!Objects.isNull(user.getId())) {
			query.addCriteria(Criteria.where("id").is(user.getId()));
		}
		
		if (!StringUtils.isEmpty(user.getUsername())) {
			query.addCriteria(Criteria.where("username").is(user.getUsername()));
		}
		
		if (!StringUtils.isEmpty(user.getPassword())) {
			query.addCriteria(Criteria.where("password").is(user.getPassword()));
		}
				
		// find(query, entityClass, collectionName); collectionName 从结果中检索对象的集合的名称
		return mongoTemplate.find(query, User.class);
	}
	
	/**
	 * 模糊查询&指定返回字段
	 * @param startWith
	 * @param endWith
	 * @return
	 */
	public List<User> listByFuzzy(String startWith, String endWith){
		Query query = new Query();
		// 包含与不包含只能任写其一，不用同时存在包含于不包含代码
		//	query.fields().include("id"); //包含该字段
		query.fields().exclude("password");//不包含该字段
		
		if (!StringUtils.isEmpty(startWith)) {
			query.addCriteria(Criteria.where("username").regex("^" + startWith));
		}
		
		if (!StringUtils.isEmpty(endWith)) {
			query.addCriteria(Criteria.where("username").regex(endWith + "$"));
		}
		
		return mongoTemplate.find(query, User.class);
	}
	
	// ~ 多表联查
	// ==============================================================

	
}
