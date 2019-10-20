package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.PaymentType;

public interface PaymentTypeRepository {

	List<PaymentType> list();
	
	PaymentType getById(Long id);
	
	PaymentType save(PaymentType paymentType);
	
	void remove(Long id);
}
