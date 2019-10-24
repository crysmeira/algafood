package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.service.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public List<City> list() {
		return cityService.list();
	}
	
	@GetMapping("/{cityId}")
	public ResponseEntity<City> getById(@PathVariable Long cityId) {
		City city = cityService.getById(cityId);
		
		if (city != null) return ResponseEntity.ok(city);
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody City city) {
		try {
			city = cityService.save(city);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(city);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{cityId}")
	public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
		try {
			city = cityService.update(cityId, city);
			
			return ResponseEntity.ok(city);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{cityId}")
	public ResponseEntity<City> remove(@PathVariable Long cityId) {
		try {
			cityService.remove(cityId);	
			
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
