package com.triajiramadhan.quiz.dto.question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuestionUpdateReqDto {

	@NotBlank(message = "field required")
	private String id;
	
	@NotBlank(message = "field required")
	private String question;

	@NotNull(message = "field required")
	private Integer version;
	
	@NotNull(message = "field required")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
