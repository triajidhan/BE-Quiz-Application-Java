package com.triajiramadhan.quiz.dao;

import java.util.Optional;

import com.triajiramadhan.quiz.model.UserCandidate;

public interface UserCandidateDao extends BaseDao<UserCandidate> {
	Optional<UserCandidate> getByUserName(String userName);
}
