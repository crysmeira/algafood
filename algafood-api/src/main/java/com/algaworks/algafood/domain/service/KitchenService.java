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
import com.algaworks.algafood.domain.repository.KitchenRepository;

@Service
public class KitchenService {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Kitchen> list() {
		return kitchenRepository.findAll();
	}
	
	public Kitchen getById(Long id) {
		Optional<Kitchen> kitchen =  kitchenRepository.findById(id);
		
		if (kitchen.isPresent()) {
			return kitchen.get();
		}
		
		throw new EntityNotFoundException(String.format("There is no kitchen for id %d", id));
	}
	
	public Kitchen update(Long kitchenId, Kitchen kitchen) {
		Optional<Kitchen> currentKitchen = kitchenRepository.findById(kitchenId);
		
		if (!currentKitchen.isPresent()) throw new EntityNotFoundException(String.format("There is no kitchen for id %d", kitchenId));
		
		BeanUtils.copyProperties(kitchen, currentKitchen.get(), "id");
		
		return kitchenRepository.save(currentKitchen.get());
	}
	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	public void remove(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("There is no kitchen for id %d", kitchenId));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Kitchen for id %d cannot be removed because it is in use", kitchenId));
		}
	}
	
}
