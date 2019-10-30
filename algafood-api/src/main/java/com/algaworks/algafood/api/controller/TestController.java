package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@GetMapping("/restaurants/with-free-delivery")
	public List<Restaurant> restaurantsWithFreeDelivery(String name) {
		return restaurantRepository.findWithFreeDelivery(name);
	}
	
	@GetMapping("/restaurants/get-first")
	public Optional<Restaurant> restaurantsGetFirst() {
		return restaurantRepository.getFirst();
	}
	
	@GetMapping("/kitchens/get-first")
	public Optional<Kitchen> kitchensGetFirst() {
		return kitchenRepository.getFirst();
	}
	
}
