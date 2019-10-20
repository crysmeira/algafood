package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.repository.KitchenRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Restaurant> list() {
		return restaurantRepository.list();
	}
	
	public Restaurant getById(Long id) {
		return restaurantRepository.getById(id);
	}
	
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.getById(kitchenId);
		
		if (kitchen == null) throw new EntityNotFoundException(String.format("There is no kitchen for id %d", kitchenId));
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant);
	}
	
	public void remove(Long restaurantId) {
		try {
			restaurantRepository.remove(restaurantId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("There is no restaurant for id %d", restaurantId));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Restaurant for id %d cannot be removed because it is in use", restaurantId));
		}
	}
	
}
