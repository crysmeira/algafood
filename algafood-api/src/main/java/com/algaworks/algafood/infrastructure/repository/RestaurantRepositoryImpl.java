package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Restaurant> list() {
		return entityManager.createQuery("from Restaurant", Restaurant.class).getResultList();
	}
	
	@Override
	public Restaurant getById(Long id) {
		return entityManager.find(Restaurant.class, id);
	}
	
	@Transactional
	@Override
	public Restaurant save(Restaurant restaurant) {
		return entityManager.merge(restaurant);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		Restaurant restaurant = getById(id);
		
		if (restaurant == null) throw new EmptyResultDataAccessException(1);
		
		entityManager.remove(restaurant);
	}

}
