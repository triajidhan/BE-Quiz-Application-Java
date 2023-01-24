package com.triajiramadhan.quiz.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
	
	T insert(T data);

	T update(T data);

	Optional<T> getById(String id);

	List<T> getAll();

	boolean deleteById(String id);

}
