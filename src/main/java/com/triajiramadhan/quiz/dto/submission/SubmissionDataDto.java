package com.triajiramadhan.quiz.dto.submission;

import java.util.List;

import com.triajiramadhan.quiz.dto.answer.AnswerDataDto;

public class SubmissionDataDto {

	private String id;
	private String question;
	private String userAnswer;
	private Boolean result;
	private Integer version;
	private Boolean isActive;
	private List<AnswerDataDto> answers;

	public String getId() {
		return id;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
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

	public List<AnswerDataDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDataDto> answers) {
		this.answers = answers;
	}
}
