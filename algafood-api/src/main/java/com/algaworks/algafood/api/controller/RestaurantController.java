package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping
	public List<Restaurant> list() {
		return restaurantService.list();
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> getById(@PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.getById(restaurantId);
		
		if (restaurant != null) return ResponseEntity.ok(restaurant);
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.save(restaurant);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.update(restaurantId, restaurant);
			
			return ResponseEntity.ok(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> remove(@PathVariable Long restaurantId) {
		try {
			restaurantService.remove(restaurantId);	
			
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PatchMapping("/{restaurantId}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
		Restaurant currentRestaurant = restaurantService.getById(restaurantId);
		
		if (currentRestaurant == null) return ResponseEntity.notFound().build();
		
		merge(fields, currentRestaurant);
		
		return update(restaurantId, currentRestaurant);
	}

	private void merge(Map<String, Object> sourceData, Restaurant targetRestaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant sourceRestaurant = objectMapper.convertValue(sourceData, Restaurant.class);
		
		sourceData.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, sourceRestaurant);
			
			ReflectionUtils.setField(field, targetRestaurant, newValue);
		});
	}
	
}
