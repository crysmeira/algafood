package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
	public ResponseEntity<Kitchen> getById(@PathVariable Long kitchenId) {
		Kitchen kitchen = kitchenService.getById(kitchenId);
		
		if (kitchen != null) return ResponseEntity.ok(kitchen);
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Kitchen kitchen) {
		try {
			kitchen = kitchenService.save(kitchen);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(kitchen);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{kitchenId}")
	public ResponseEntity<?> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
		try {
			Kitchen currentKitchen = kitchenService.getById(kitchenId);
			
			if (currentKitchen == null) return ResponseEntity.notFound().build();
			
			BeanUtils.copyProperties(kitchen, currentKitchen, "id");
			
			currentKitchen = kitchenService.save(currentKitchen);
			return ResponseEntity.ok(currentKitchen);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> remove(@PathVariable Long kitchenId) {
		try {
			kitchenService.remove(kitchenId);	
			
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
