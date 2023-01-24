package com.triajiramadhan.quiz.service;

import java.util.List;

import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.report.ReportDataDto;
import com.triajiramadhan.quiz.dto.report.ReportInsertReqDto;

public interface ReportService {

	BaseInsertResDto insert(ReportInsertReqDto data);
	
	BaseDataGetResDto<ReportDataDto> getById(String id);

	BaseDataGetResDto<List<ReportDataDto>> getAll();
	
	BaseDataGetResDto<List<ReportDataDto>> getAllByUserCandidateId(String userCandidateId);
	
	BaseDataGetResDto<List<ReportDataDto>> getHighestScore();
	
}
