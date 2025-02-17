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

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.service.KitchenService;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

	@Autowired
	private KitchenService kitchenService;
	
	@GetMapping
	public List<Kitchen> list() {
		return kitchenService.list();
	}
	
	@GetMapping("/{kitchenId}")
	public Kitchen getById(@PathVariable Long kitchenId) {
		return kitchenService.getById(kitchenId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}
	
	@PutMapping("/{kitchenId}")
	public Kitchen update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
		return kitchenService.update(kitchenId, kitchen);
	}
	
	@DeleteMapping("/{kitchenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long kitchenId) {
		kitchenService.remove(kitchenId);
	}
	
}
