package com.triajiramadhan.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_user_candidate", uniqueConstraints = 
		@UniqueConstraint(
				name = "user_candidate_ck",
				columnNames = {"user_name", "email"}
			)
	)
public class UserCandidate extends BaseModel {

	@Column(name = "full_name", nullable = false, length = 50)
	private String fullName;
	
	@Column(name = "user_name", unique = true, nullable = false, length = 50)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;

	public String getFullName() {
		return fullName;
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
