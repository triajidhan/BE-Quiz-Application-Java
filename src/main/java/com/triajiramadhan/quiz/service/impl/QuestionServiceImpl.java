package com.triajiramadhan.quiz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triajiramadhan.quiz.dao.AnswerDao;
import com.triajiramadhan.quiz.dao.QuestionDao;
import com.triajiramadhan.quiz.dto.answer.AnswerDataDto;
import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataUpdateResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.question.QuestionDataDto;
import com.triajiramadhan.quiz.dto.question.QuestionInsertReqDto;
import com.triajiramadhan.quiz.dto.question.QuestionUpdateReqDto;
import com.triajiramadhan.quiz.model.Answer;
import com.triajiramadhan.quiz.model.Question;
import com.triajiramadhan.quiz.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private AnswerDao answerDao;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseInsertResDto insert(QuestionInsertReqDto data) {
		final BaseDataInsertResDto baseDataInsertResDto = new BaseDataInsertResDto();
		final BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

		Question questionInsert = new Question();
		questionInsert.setQuestion(data.getQuestion());
		questionInsert = questionDao.insert(questionInsert);

		for (int i = 0; i < data.getAnswers().size(); i++) {
			Answer answerInsert = new Answer();
			answerInsert.setQuestion(questionInsert);
			answerInsert.setAnswer(data.getAnswers().get(i).getAnswer());
			answerInsert.setAnswerKey(data.getAnswers().get(i).getAnswerKey());
			answerInsert = answerDao.insert(answerInsert);
		}

		baseDataInsertResDto.setId(questionInsert.getId());
		baseInsertResDto.setData(baseDataInsertResDto);
		baseInsertResDto.setMessage("Question Added Successfully!");
		return baseInsertResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseUpdateResDto update(QuestionUpdateReqDto data) {
		final Optional<Question>optional = questionDao.getById(data.getId());
		final BaseDataUpdateResDto baseDataUpdateResDto = new BaseDataUpdateResDto();
		final BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();
		if(optional.isPresent()) {
			Question questionUpdate = optional.get();
			if(data.getQuestion() != null || !data.getQuestion().equalsIgnoreCase("")) {
				if (questionUpdate.getVersion() == data.getVersion()) {
					questionUpdate.setQuestion(data.getQuestion());
					questionUpdate.setIsActive(data.getIsActive());
					questionUpdate = questionDao.update(questionUpdate);
					baseDataUpdateResDto.setVersion(questionUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Updating Question Success!");					
				} else {
					baseDataUpdateResDto.setVersion(questionUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Version is Not Uptodate!");
				}
			} 
		}else {
			baseUpdateResDto.setData(baseDataUpdateResDto);
			baseUpdateResDto.setMessage("Question Not Found!");
		}
		return baseUpdateResDto;
	}

	@Override
	public BaseDataGetResDto<QuestionDataDto> getById(String id) {
		final Optional<Question> optional = questionDao.getById(id);
		final BaseDataGetResDto<QuestionDataDto> baseDataGetResDto = new BaseDataGetResDto<>();
		final QuestionDataDto questionDataDto = new QuestionDataDto();
		if(optional.isPresent()) {
			questionDataDto.setQuestion(optional.get().getQuestion());
			questionDataDto.setId(optional.get().getId());
			questionDataDto.setVersion(optional.get().getVersion());
			questionDataDto.setIsActive(optional.get().getIsActive());
			
			final List<AnswerDataDto> answerDataDtos = new ArrayList<>();
			List<Answer> answers = new ArrayList<>();
			answers = answerDao.getByQuestionId(id);		
			for (int i = 0; i < answers.size(); i++) {
				final AnswerDataDto answerDataDto = new AnswerDataDto();
				answerDataDto.setAnswer(answers.get(i).getAnswer());
				answerDataDto.setAnswerKey(answers.get(i).getAnswerKey());
				answerDataDto.setId(answers.get(i).getId());
				answerDataDto.setIsActive(answers.get(i).getIsActive());
				answerDataDto.setVersion(answers.get(i).getVersion());
				answerDataDtos.add(answerDataDto);
			}
			questionDataDto.setAnswers(answerDataDtos);
		}
		baseDataGetResDto.setData(questionDataDto);
		return baseDataGetResDto;
	}

	@Override
	public BaseDataGetResDto<List<QuestionDataDto>> getAll() {
		final List<Question> questions = questionDao.getAll();
		final List<QuestionDataDto> questionDataDtos = new ArrayList<>();
		final BaseDataGetResDto<List<QuestionDataDto>> baseDataGetResDto = new BaseDataGetResDto<>();
		if (questions.size() > 0) {
			for (Question question : questions) {
				QuestionDataDto questionDataDto = new QuestionDataDto();
				questionDataDto.setQuestion(question.getQuestion());
				questionDataDto.setId(question.getId());
				questionDataDto.setVersion(question.getVersion());
				questionDataDto.setIsActive(question.getIsActive());
				
				final List<AnswerDataDto> answerDataDtos = new ArrayList<>();
				List<Answer> answers = new ArrayList<>();
				answers = answerDao.getByQuestionId(question.getId());
				for (int i = 0; i < answers.size(); i++) {
					final AnswerDataDto answerDataDto = new AnswerDataDto();
					answerDataDto.setAnswer(answers.get(i).getAnswer());
					answerDataDto.setAnswerKey(answers.get(i).getAnswerKey());
					answerDataDto.setId(answers.get(i).getId());
					answerDataDto.setIsActive(answers.get(i).getIsActive());
					answerDataDto.setVersion(answers.get(i).getVersion());
					answerDataDtos.add(answerDataDto);
				}
				questionDataDto.setAnswers(answerDataDtos);
				questionDataDtos.add(questionDataDto);
			}
		}
		baseDataGetResDto.setData(questionDataDtos);
		return baseDataGetResDto;
	}

	@Override
	public BaseDataGetResDto<List<QuestionDataDto>> getRandomQuestion() {
		final List<Question> questions = questionDao.getRandomQuestion(5);
		final List<QuestionDataDto> questionDataDtos = new ArrayList<>();
		final BaseDataGetResDto<List<QuestionDataDto>> baseDataGetResDto = new BaseDataGetResDto<>();
		if (questions.size() > 0) {
			for (Question question : questions) {
				QuestionDataDto questionDataDto = new QuestionDataDto();
				questionDataDto.setQuestion(question.getQuestion());
				questionDataDto.setId(question.getId());
				questionDataDto.setVersion(question.getVersion());
				questionDataDto.setIsActive(question.getIsActive());
				
				final List<AnswerDataDto> answerDataDtos = new ArrayList<>();
				List<Answer> answers = new ArrayList<>();
				answers = answerDao.getByQuestionId(question.getId());
				for (int i = 0; i < answers.size(); i++) {
					final AnswerDataDto answerDataDto = new AnswerDataDto();
					answerDataDto.setAnswer(answers.get(i).getAnswer());
					answerDataDto.setAnswerKey(answers.get(i).getAnswerKey());
					answerDataDto.setId(answers.get(i).getId());
					answerDataDto.setIsActive(answers.get(i).getIsActive());
					answerDataDto.setVersion(answers.get(i).getVersion());
					answerDataDtos.add(answerDataDto);
				}
				questionDataDto.setAnswers(answerDataDtos);
				questionDataDtos.add(questionDataDto);
			}
		}
		baseDataGetResDto.setData(questionDataDtos);
		return baseDataGetResDto;
	}

}
