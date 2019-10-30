package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withFreeDelivery;
import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@Autowired
	@Lazy
	public RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> findWithFreeDelivery(String name) {
		return restaurantRepository.findAll(withFreeDelivery().and(withSimilarName(name)));
	}
}
