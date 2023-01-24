package com.triajiramadhan.quiz.dao;

import java.util.List;

import com.triajiramadhan.quiz.model.Report;

public interface ReportDao extends BaseDao<Report> {
		
	List<Report> getAllByUserCandidateId(String userCandidateId);
	
}
