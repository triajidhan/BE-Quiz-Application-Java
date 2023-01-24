package com.triajiramadhan.quiz.dto.question;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.triajiramadhan.quiz.dto.answer.AnswerInsertReqDto;


public class QuestionInsertReqDto {

	@NotBlank(message = "field required")
	private String question;
	
	@Size(min = 4, message = "there are at least 4 multiple choice")
	private List<AnswerInsertReqDto> answers;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<AnswerInsertReqDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerInsertReqDto> answers) {
		this.answers = answers;
	}
}
