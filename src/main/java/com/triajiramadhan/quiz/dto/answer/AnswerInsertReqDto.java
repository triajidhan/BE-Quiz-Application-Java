package com.triajiramadhan.quiz.dto.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnswerInsertReqDto {

	@NotBlank(message = "field required")
	private String answer;
	
	@NotNull(message = "field required")
	private Boolean answerKey;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Boolean getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(Boolean answerKey) {
		this.answerKey = answerKey;
	}
}
