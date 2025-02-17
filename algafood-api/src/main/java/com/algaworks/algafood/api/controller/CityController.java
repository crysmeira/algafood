package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public City getById(@PathVariable Long cityId) {
		return cityService.getById(cityId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City save(@RequestBody City city) {
		return cityService.save(city);
	}
	
	@PutMapping("/{cityId}")
	public City update(@PathVariable Long cityId, @RequestBody City city) {
		return cityService.update(cityId, city);
	}
	
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long cityId) {
		cityService.remove(cityId);	
	}
	
}
