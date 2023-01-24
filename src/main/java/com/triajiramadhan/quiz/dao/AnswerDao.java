package com.triajiramadhan.quiz.dao;

import java.util.List;
import com.triajiramadhan.quiz.model.Answer;

public interface AnswerDao extends BaseDao<Answer> {
	
	List<Answer> getByQuestionId(String questionId);

}
