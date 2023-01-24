package com.triajiramadhan.quiz.service;

import java.util.List;

import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.question.QuestionDataDto;
import com.triajiramadhan.quiz.dto.question.QuestionInsertReqDto;
import com.triajiramadhan.quiz.dto.question.QuestionUpdateReqDto;

public interface QuestionService {

	BaseInsertResDto insert(QuestionInsertReqDto data);

	BaseUpdateResDto update(QuestionUpdateReqDto data);

	BaseDataGetResDto<QuestionDataDto> getById(String id);

	BaseDataGetResDto<List<QuestionDataDto>> getAll();
	
	BaseDataGetResDto<List<QuestionDataDto>> getRandomQuestion();
	
}
