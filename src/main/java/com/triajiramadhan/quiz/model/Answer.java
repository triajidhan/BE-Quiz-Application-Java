package com.triajiramadhan.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_answer")
public class Answer extends BaseModel {
	
	@Column(name = "answer", nullable = false)
	private String answer;
	
	@Column(name = "answer_key", nullable = false)
	private Boolean answerKey;
	
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
