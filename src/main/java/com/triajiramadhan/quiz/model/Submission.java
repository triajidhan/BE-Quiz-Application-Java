package com.triajiramadhan.quiz.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_submission")
public class Submission extends BaseModel {
	
	@ManyToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;
	
	@ManyToOne
	@JoinColumn(name = "report_id")
	private Report report;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
