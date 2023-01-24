package com.triajiramadhan.quiz.dao;

import java.util.List;

import com.triajiramadhan.quiz.model.Question;

public interface QuestionDao extends BaseDao<Question> {

	List<Question> getRandomQuestion(Integer limit);
}
