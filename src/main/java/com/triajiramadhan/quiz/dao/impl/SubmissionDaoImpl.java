package com.triajiramadhan.quiz.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.triajiramadhan.quiz.dao.SubmissionDao;
import com.triajiramadhan.quiz.model.Submission;

@Repository
public class SubmissionDaoImpl extends BaseDaoImpl implements SubmissionDao {

	@Override
	public Submission insert(Submission data) {
		this.em.persist(data);
		return data;
	}

	@Override
	public List<Submission> getAll() {
		final String sql = " SELECT * FROM t_submission ts "
				+ "INNER JOIN t_report tr ON tr.id = ts.report_id "
				+ "INNER JOIN t_answer ta ON ta.id = ts.answer_id ";
		@SuppressWarnings("unchecked")
		final List<Submission> submissions = em.createNativeQuery(sql, Submission.class)
				.getResultList();
		return submissions;
	}

	@Override
	public List<Submission> getAllByReportId(String reportId) {
		final String sql = " SELECT * FROM t_submission ts "
				+ "INNER JOIN t_report tr ON tr.id = ts.report_id "
				+ "INNER JOIN t_answer ta ON ta.id = ts.answer_id "
				+ "WHERE tr.id = :reportId";
		@SuppressWarnings("unchecked")
		final List<Submission> submissions = em.createNativeQuery(sql, Submission.class)
				.setParameter("reportId", reportId)
				.getResultList();
		return submissions;
	}

}
