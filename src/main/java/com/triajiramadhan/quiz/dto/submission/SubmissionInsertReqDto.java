package com.triajiramadhan.quiz.dto.submission;

import javax.validation.constraints.NotBlank;

public class SubmissionInsertReqDto {

	@NotBlank(message = "field required")
	private String answerId;

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

}
