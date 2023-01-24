package com.triajiramadhan.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_report")
public class Report extends BaseModel {

	@Column(name = "score")
	private Double score;
	
	@ManyToOne
	@JoinColumn(name = "user_candidate_id", nullable = false)
	private UserCandidate userCandidate;

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public UserCandidate getUserCandidate() {
		return userCandidate;
	}

	public void setUserCandidate(UserCandidate userCandidate) {
		this.userCandidate = userCandidate;
	}
}
