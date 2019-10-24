package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private KitchenRepository kitchenRepository;
	
//	@GetMapping("/kitchens/by-name")
//	public List<Kitchen> kitchenByName(@RequestParam("name") String name) {
//		return kitchenRepository.getByName(name);
//	}
}
