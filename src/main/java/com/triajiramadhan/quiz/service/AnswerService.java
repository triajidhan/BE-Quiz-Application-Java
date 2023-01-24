package com.triajiramadhan.quiz.service;

import com.triajiramadhan.quiz.dto.answer.AnswerUpdateReqDto;
import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;

public interface AnswerService {

	BaseUpdateResDto update(AnswerUpdateReqDto data);

	BaseDeleteResDto deleteById(String id);
	
}
