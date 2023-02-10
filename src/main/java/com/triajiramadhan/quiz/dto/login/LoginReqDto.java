package com.triajiramadhan.quiz.dto.login;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginReqDto {

	@NotBlank(message = "field required")
	@Size(max = 50)
	private String userName;
	
	@NotBlank(message = "field required")
	private String password;

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
}
