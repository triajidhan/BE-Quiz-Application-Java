package com.triajiramadhan.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_user", uniqueConstraints = 
		@UniqueConstraint(
				name = "user_ck",
				columnNames = {"user_name", "email"}
			)
	)
public class User extends BaseModel {

	@Column(name = "full_name", nullable = false, length = 50)
	private String fullName;
	
	@Column(name = "user_name", unique = true, nullable = false, length = 50)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;
	
	@Column(name = "role_code", nullable = false, length = 3)
	private String roleCode;

	public String getFullName() {
		return fullName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
