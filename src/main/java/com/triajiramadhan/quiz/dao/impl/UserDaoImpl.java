package com.triajiramadhan.quiz.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.triajiramadhan.quiz.dao.UserDao;
import com.triajiramadhan.quiz.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public User insert(User data) {
		this.em.persist(data);
		return data;
	}

	@Override
	public Optional<User> getById(String id) {
		final User user = this.em.find(User.class, id);
		this.em.detach(user);
		final Optional<User> userOptional = Optional.ofNullable(user);
		return userOptional;
	}

	@Override
	public List<User> getAll() {
		final String sql = " SELECT * FROM t_user WHERE role_code LIKE 'CDT'";
		@SuppressWarnings("unchecked")
		final List<User> userCandidates = em.createNativeQuery(sql, User.class).getResultList();
		return userCandidates;
	}

	@Override
	public User update(User data) {
		final User userUpdated = this.em.merge(data);
		this.em.flush();
		return userUpdated;
	}

	@Override
	public boolean deleteById(String id) {
		final String sql = " DELETE FROM t_user WHERE id = :id ";
		final int result = this.em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}
	
	@Override
	public Optional<User> getByUserName(final String userName) {
		final String sql = " SELECT * FROM t_user WHERE user_name = :userName AND is_active = true";

		User userCandidate = null;
		try {
			userCandidate = (User) this.em.createNativeQuery(sql, User.class)
					.setParameter("userName", userName)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(userCandidate);
	}

}
