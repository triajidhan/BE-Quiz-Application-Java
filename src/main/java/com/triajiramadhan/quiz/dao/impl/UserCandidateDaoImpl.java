package com.triajiramadhan.quiz.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.triajiramadhan.quiz.dao.UserCandidateDao;
import com.triajiramadhan.quiz.model.UserCandidate;

@Repository
public class UserCandidateDaoImpl extends BaseDaoImpl implements UserCandidateDao {

	@Override
	public UserCandidate insert(UserCandidate data) {
		this.em.persist(data);
		return data;
	}

	@Override
	public Optional<UserCandidate> getById(String id) {
		final UserCandidate userCandidate = this.em.find(UserCandidate.class, id);
		this.em.detach(userCandidate);
		final Optional<UserCandidate> userCandidateOptional = Optional.ofNullable(userCandidate);
		return userCandidateOptional;
	}

	@Override
	public List<UserCandidate> getAll() {
		final String sql = " SELECT * FROM t_user_candidate";
		@SuppressWarnings("unchecked")
		final List<UserCandidate> userCandidates = em.createNativeQuery(sql, UserCandidate.class).getResultList();
		return userCandidates;
	}

	@Override
	public UserCandidate update(UserCandidate data) {
		final UserCandidate userCandidateUpdated = this.em.merge(data);
		this.em.flush();
		return userCandidateUpdated;
	}

	@Override
	public boolean deleteById(String id) {
		final String sql = " DELETE FROM t_user_candidate WHERE id = :id ";
		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}
	
	@Override
	public Optional<UserCandidate> getByUserName(final String userName) {
		final String sql = " SELECT * FROM t_user_candidate WHERE user_name = :userName AND is_active = true";

		UserCandidate userCandidate = null;
		try {
			userCandidate = (UserCandidate) this.em.createNativeQuery(sql, UserCandidate.class)
					.setParameter("userName", userName)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(userCandidate);
	}

}
