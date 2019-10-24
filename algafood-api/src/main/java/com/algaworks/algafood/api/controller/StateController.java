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
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.service.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping
	public List<State> list() {
		return stateService.list();
	}
	
	@GetMapping("/{stateId}")
	public ResponseEntity<State> getById(@PathVariable Long stateId) {
		State state = stateService.getById(stateId);
		
		if (state != null) return ResponseEntity.ok(state);
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody State state) {
		try {
			state = stateService.save(state);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(state);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{stateId}")
	public ResponseEntity<?> update(@PathVariable Long stateId, @RequestBody State state) {
		try {
			state = stateService.update(stateId, state);
			
			return ResponseEntity.ok(state);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{stateId}")
	public ResponseEntity<State> remove(@PathVariable Long stateId) {
		try {
			stateService.remove(stateId);	
			
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
