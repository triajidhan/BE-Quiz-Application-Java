package com.triajiramadhan.quiz.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.triajiramadhan.quiz.dao.ReportDao;
import com.triajiramadhan.quiz.model.Report;

@Repository
public class ReportDaoImpl extends BaseDaoImpl implements ReportDao {

	@Override
	public Report insert(Report data) {
		this.em.persist(data);
		return data;
	}
	
	@Override
	public Optional<Report> getById(String id) {
		final Report report = this.em.find(Report.class, id);
		this.em.detach(report);
		final Optional<Report> reportOptional = Optional.ofNullable(report);
		return reportOptional;
	}
	
	@Override
	public Report update(Report data) {
		final Report reportUpdated = this.em.merge(data);
		this.em.flush();
		return reportUpdated;
	}

	@Override
	public boolean deleteById(String id) {
		final String sql = " DELETE FROM t_report WHERE id = :id ";
		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

	@Override
	public List<Report> getAll() {
		final String sql = " SELECT * FROM t_report tr "
				+ "INNER JOIN t_user_candidate tuc ON tuc.id = tr.user_candidate_id ";
		@SuppressWarnings("unchecked")
		final List<Report> reports = em.createNativeQuery(sql, Report.class).getResultList();
		return reports;
	}

	@Override
	public List<Report> getAllByUserCandidateId(String userCandidateId) {
		final String sql = " SELECT * FROM t_report tr "
				+ "INNER JOIN t_user_candidate tuc ON tuc.id = tr.user_candidate_id "
				+ "WHERE tuc.id = :userCandidateId";
		@SuppressWarnings("unchecked")
		final List<Report> reports = em.createNativeQuery(sql, Report.class)
				.setParameter("userCandidateId", userCandidateId)
				.getResultList();
		return reports;
	}

}
