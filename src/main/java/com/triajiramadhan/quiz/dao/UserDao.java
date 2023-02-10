package com.triajiramadhan.quiz.dao;

import java.util.Optional;

import com.triajiramadhan.quiz.model.User;

public interface UserDao extends BaseDao<User> {
	Optional<User> getByUserName(String userName);
}
