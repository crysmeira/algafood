package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
		return cityRepository.findAll();
	}
	
	public City getById(Long id) {
		Optional<City> city = cityRepository.findById(id);
		
		if (city.isPresent()) return city.get();
		
		return null;
	}
	
	public City update(Long cityId, City city) {
		Optional<City> currentCity = cityRepository.findById(cityId);
		
		if (!currentCity.isPresent()) throw new EntityNotFoundException(String.format("There is no city for id %d", cityId));
		
		BeanUtils.copyProperties(city, currentCity.get(), "id");
		
		return cityRepository.save(currentCity.get());
	}
	
	public City save(City city) {
		Long stateId = city.getState().getId();
		State state = stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException(String.format("There is no state for id %d", stateId)));
		
		city.setState(state);
		
		return cityRepository.save(city);
	}
	
	public void remove(Long cityId) {
		try {
			cityRepository.deleteById(cityId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("There is no city for id %d", cityId));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("City for id %d cannot be removed because it is in use", cityId));
		}
	}
	
}
