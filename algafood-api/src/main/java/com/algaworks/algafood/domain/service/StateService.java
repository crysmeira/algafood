package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	public List<State> list() {
		return stateRepository.list();
	}
	
	public State getById(Long id) {
		return stateRepository.getById(id);
	}
	
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	public void remove(Long stateId) {
		try {
			stateRepository.remove(stateId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("There is no state for id %d", stateId));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("State for id %d cannot be removed because it is in use", stateId));
		}
	}
	
}
