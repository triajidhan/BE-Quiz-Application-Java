package com.triajiramadhan.quiz.dao;

import java.util.List;
import com.triajiramadhan.quiz.model.Submission;

public interface SubmissionDao {
	
	Submission insert(Submission data);

	List<Submission> getAll();

	List<Submission> getAllByReportId(String reportId);

}
