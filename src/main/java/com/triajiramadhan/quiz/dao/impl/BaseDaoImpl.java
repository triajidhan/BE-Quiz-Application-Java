package com.triajiramadhan.quiz.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoImpl {
	protected EntityManager em;
	
	@PersistenceContext
	public void setEm( EntityManager em) {
		this.em = em;
	}
}
