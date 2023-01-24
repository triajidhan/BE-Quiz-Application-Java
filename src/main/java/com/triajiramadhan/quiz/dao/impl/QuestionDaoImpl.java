package com.triajiramadhan.quiz.dao.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.triajiramadhan.quiz.dao.QuestionDao;
import com.triajiramadhan.quiz.model.Question;

@Repository
public class QuestionDaoImpl extends BaseDaoImpl implements QuestionDao {

	@Override
	public Question insert(Question data) {
		this.em.persist(data);
		return data;
	}

	@Override
	public Optional<Question> getById(String id) {
		final Question question = this.em.find(Question.class, id);
		this.em.detach(question);
		final Optional<Question> questionOptional = Optional.ofNullable(question);
		return questionOptional;
	}

	@Override
	public Question update(Question data) {
		final Question questionUpdated = this.em.merge(data);
		this.em.flush();
		return questionUpdated;
	}

	@Override
	public boolean deleteById(String id) {
		final String sql = " DELETE FROM t_question WHERE id = :id ";
		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}
	
	@Override
	public List<Question> getAll() {
		final String sql = " SELECT * FROM t_question";
		@SuppressWarnings("unchecked")
		final List<Question> questions = em.createNativeQuery(sql, Question.class).getResultList();
		return questions;
	}

	@Override
	public List<Question> getRandomQuestion(Integer limit) {
		final String sql = " SELECT * FROM t_question "
				+ "ORDER BY random() LIMIT :limit";
		@SuppressWarnings("unchecked")
		final List<Question> questions = em.createNativeQuery(sql, Question.class)
				.setParameter("limit", limit)
				.getResultList();
		return questions;
	}

}
