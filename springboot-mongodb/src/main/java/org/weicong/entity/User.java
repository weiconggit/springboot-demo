package org.weicong.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("测试用户实体")
public class User implements Serializable{
	
	private static final long serialVersionUID = -4728572769024288419L;
	
	@ApiModelProperty("用户id")
	private Long id;
	
	@ApiModelProperty("用户姓名/用户名")
    private String username;
	
	@ApiModelProperty("密码")
    private String password;
	
	public User() {}
	
	public User(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	

    
}
