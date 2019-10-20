package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<City> list() {
		return cityRepository.list();
	}
	
	public City getById(Long id) {
		return cityRepository.getById(id);
	}
	
	public City save(City city) {
		Long stateId = city.getState().getId();
		State state = stateRepository.getById(stateId);
		
		if (state == null) throw new EntityNotFoundException(String.format("There is no state for id %d", stateId));
		
		city.setState(state);
		
		return cityRepository.save(city);
	}
	
	public void remove(Long cityId) {
		try {
			cityRepository.remove(cityId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("There is no city for id %d", cityId));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("City for id %d cannot be removed because it is in use", cityId));
		}
	}
	
}
