package com.triajiramadhan.quiz.dto.base;


public class BaseUpdateResDto {

	private BaseDataUpdateResDto data;
	private String message;
	
	public BaseDataUpdateResDto getData() {
		return data;
	}
	
	public void setData(BaseDataUpdateResDto data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
