package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;

@Component
public class CityRepositoryImpl implements CityRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<City> list() {
		return entityManager.createQuery("from City", City.class).getResultList();
	}
	
	@Override
	public City getById(Long id) {
		return entityManager.find(City.class, id);
	}
	
	@Transactional
	@Override
	public City save(City city) {
		return entityManager.merge(city);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		City city = getById(id);
		
		if (city == null) throw new EmptyResultDataAccessException(1);
		
		entityManager.remove(city);
	}

}
