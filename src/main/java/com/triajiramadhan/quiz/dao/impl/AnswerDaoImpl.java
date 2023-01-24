package com.triajiramadhan.quiz.dao.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.triajiramadhan.quiz.dao.AnswerDao;
import com.triajiramadhan.quiz.model.Answer;

@Repository
public class AnswerDaoImpl extends BaseDaoImpl implements AnswerDao {

	@Override
	public Answer insert(Answer data) {
		this.em.persist(data);
		return data;
	}

	@Override
	public Optional<Answer> getById(String id) {
		final Answer answer = this.em.find(Answer.class, id);
		this.em.detach(answer);
		final Optional<Answer> answerOptional = Optional.ofNullable(answer);
		return answerOptional;
	}

	@Override
	public Answer update(Answer data) {
		final Answer answerUpdated = this.em.merge(data);
		this.em.flush();
		return answerUpdated;
	}

	@Override
	public boolean deleteById(String id) {
		final String sql = " DELETE FROM t_answer WHERE id = :id ";
		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

	@Override
	public List<Answer> getAll() {
		final String sql = " SELECT * FROM t_answer ta "
				+ "INNER JOIN t_question tq ON tq.id = ta.question_id ";
		@SuppressWarnings("unchecked")
		final List<Answer> answers = em.createNativeQuery(sql, Answer.class).getResultList();
		return answers;
	}

	@Override
	public List<Answer> getByQuestionId(String questionId) {
		final String sql = " SELECT * FROM t_answer ta "
				+ "INNER JOIN t_question tq ON tq.id = ta.question_id "
				+ "WHERE tq.id = :questionId";
		@SuppressWarnings("unchecked")
		final List<Answer> answers = em.createNativeQuery(sql, Answer.class)
				.setParameter("questionId", questionId)
				.getResultList();
		return answers;
	}

}
