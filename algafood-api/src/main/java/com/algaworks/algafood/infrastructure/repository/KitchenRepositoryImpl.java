package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Kitchen> list() {
		return entityManager.createQuery("from Kitchen", Kitchen.class).getResultList();
	}
	
	@Override
	public Kitchen getById(Long id) {
		return entityManager.find(Kitchen.class, id);
	}
	
	@Transactional
	@Override
	public Kitchen save(Kitchen kitchen) {
		return entityManager.merge(kitchen);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		Kitchen kitchen = getById(id);
		
		if (kitchen == null) throw new EmptyResultDataAccessException(1);
		
		entityManager.remove(kitchen);
	}

}
