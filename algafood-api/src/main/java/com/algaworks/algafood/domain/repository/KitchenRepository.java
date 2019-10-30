package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Kitchen;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {

	List<Kitchen> findByName(String name);
	
	List<Kitchen> findByName2(String name);
	
}
