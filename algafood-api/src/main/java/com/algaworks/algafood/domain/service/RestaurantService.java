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
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Restaurant> list() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant getById(Long id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
	
		if (restaurant.isPresent()) return restaurant.get();
		
		return null;
	}
	
	public Restaurant update(Long restaurantId, Restaurant restaurant) {
		Optional<Restaurant> currentRestaurant = restaurantRepository.findById(restaurantId);
		
		if (!currentRestaurant.isPresent()) throw new EntityNotFoundException(String.format("There is no restaurant for id %d", restaurantId));
		
		BeanUtils.copyProperties(restaurant, currentRestaurant.get(), "id");
		
		return restaurantRepository.save(currentRestaurant.get());
	}
	
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() -> new EntityNotFoundException(String.format("There is no kitchen for id %d", kitchenId)));
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant);
	}
	
	public void remove(Long restaurantId) {
		try {
			restaurantRepository.deleteById(restaurantId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("There is no restaurant for id %d", restaurantId));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Restaurant for id %d cannot be removed because it is in use", restaurantId));
		}
	}
	
}
