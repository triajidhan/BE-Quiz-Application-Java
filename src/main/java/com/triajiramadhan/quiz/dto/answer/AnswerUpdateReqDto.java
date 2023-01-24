package com.triajiramadhan.quiz.dto.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnswerUpdateReqDto {

	@NotBlank(message = "field required")
	private String id;
	
	@NotBlank(message = "field required")
	private String answer;

	@NotNull(message = "field required")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
