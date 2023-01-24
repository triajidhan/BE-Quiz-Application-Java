package com.triajiramadhan.quiz.dto.report;

import java.util.List;

import com.triajiramadhan.quiz.dto.submission.SubmissionDataDto;

public class ReportDataDto {

	private String id;
	private String fullName;
	private Double score;
	private Integer version;
	private List<SubmissionDataDto> submissions;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Double getScore() {
		return score;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public List<SubmissionDataDto> getSubmissions() {
		return submissions;
	}
	
	public void setSubmissions(List<SubmissionDataDto> submissions) {
		this.submissions = submissions;
	}	
}
