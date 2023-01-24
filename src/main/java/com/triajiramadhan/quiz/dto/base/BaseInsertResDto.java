package com.triajiramadhan.quiz.dto.base;


public class BaseInsertResDto {

	private BaseDataInsertResDto data;
	private String message;
	
	public BaseDataInsertResDto getData() {
		return data;
	}
	
	public void setData(BaseDataInsertResDto data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
