package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

@Component
public class StateRepositoryImpl implements StateRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<State> list() {
		return entityManager.createQuery("from State", State.class).getResultList();
	}
	
	@Override
	public State getById(Long id) {
		return entityManager.find(State.class, id);
	}
	
	@Transactional
	@Override
	public State save(State state) {
		return entityManager.merge(state);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		State state = getById(id);
		
		if (state == null) throw new EmptyResultDataAccessException(1);
		
		entityManager.remove(state);
	}

}
