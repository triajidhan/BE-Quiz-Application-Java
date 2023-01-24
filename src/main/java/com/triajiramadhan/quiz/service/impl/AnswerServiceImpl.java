package com.triajiramadhan.quiz.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triajiramadhan.quiz.dao.AnswerDao;
import com.triajiramadhan.quiz.dto.answer.AnswerUpdateReqDto;
import com.triajiramadhan.quiz.dto.base.BaseDataUpdateResDto;
import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.model.Answer;
import com.triajiramadhan.quiz.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDao answerDao;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseUpdateResDto update(AnswerUpdateReqDto data) {
		final Optional<Answer>optional = answerDao.getById(data.getId());
		final BaseDataUpdateResDto baseDataUpdateResDto = new BaseDataUpdateResDto();
		final BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();
		if(optional.isPresent()) {
			Answer answerUpdate = optional.get();
			if(data.getAnswer() != null || !data.getAnswer().equalsIgnoreCase("")) {
				if (answerUpdate.getVersion() == data.getVersion()) {
					answerUpdate.setAnswer(data.getAnswer());
					answerUpdate = answerDao.update(answerUpdate);
					baseDataUpdateResDto.setVersion(answerUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Updating Answer Success!");					
				} else {
					baseDataUpdateResDto.setVersion(answerUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Version is Not Uptodate!");
				}
			} 
		}else {
			baseUpdateResDto.setData(baseDataUpdateResDto);
			baseUpdateResDto.setMessage("Answer Not Found!");
		}
		return baseUpdateResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseDeleteResDto deleteById(String id) {
		final Optional<Answer> optional = answerDao.getById(id);
		final BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();
		if (optional.isPresent()) {
			if (!optional.get().getAnswerKey()) {
				answerDao.deleteById(id);
				baseDeleteResDto.setMessage("Answer Deleted Successfully!");				
			} else {
				baseDeleteResDto.setMessage("Can't Delete Correct Answer!");
			}
		} else {
			baseDeleteResDto.setMessage("Answer Deleted Fail!");
		}
		return baseDeleteResDto;
	}

}
