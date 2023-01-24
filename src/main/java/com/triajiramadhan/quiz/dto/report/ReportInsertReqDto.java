package com.triajiramadhan.quiz.dto.report;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.triajiramadhan.quiz.dto.submission.SubmissionInsertReqDto;

public class ReportInsertReqDto {

	@NotBlank(message = "field required")
	private String userCandidateId;
	
	@Size(min = 1, message = "there are at least 1 submission")
	private List<SubmissionInsertReqDto> submissions;

	public String getUserCandidateId() {
		return userCandidateId;
	}

	public void setUserCandidateId(String userCandidateId) {
		this.userCandidateId = userCandidateId;
	}

	public List<SubmissionInsertReqDto> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<SubmissionInsertReqDto> submissions) {
		this.submissions = submissions;
	}
}
