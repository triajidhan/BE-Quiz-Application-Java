package com.triajiramadhan.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_user_candidate", uniqueConstraints = 
		@UniqueConstraint(
				name = "user_candidate_ck",
				columnNames = {"full_name", "email"}
			)
	)
public class UserCandidate extends BaseModel {

	@Column(name = "full_name", nullable = false, length = 50)
	private String fullName;
	
	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;
	
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
