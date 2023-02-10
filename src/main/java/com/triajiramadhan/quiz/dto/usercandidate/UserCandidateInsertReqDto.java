package com.triajiramadhan.quiz.dto.usercandidate;

import javax.validation.constraints.NotBlank;

public class UserCandidateInsertReqDto {

	@NotBlank(message = "field required")
	private String fullName;
	
	@NotBlank(message = "field required")
	private String userName;
	
	@NotBlank(message = "field required")
	private String password;
	
	@NotBlank(message = "field required")
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
