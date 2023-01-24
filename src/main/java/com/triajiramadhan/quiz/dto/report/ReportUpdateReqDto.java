package com.triajiramadhan.quiz.dto.report;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReportUpdateReqDto {

	@NotBlank(message = "field required")
	private String id;
	
	@NotBlank(message = "field required")
	@Min(0)
	@Max(100)
	private Double score;

	@NotNull(message = "field required")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
