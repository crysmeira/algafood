package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.PaymentType;
import com.algaworks.algafood.domain.repository.PaymentTypeRepository;

@Component
public class PaymentTypeRepositoryImpl implements PaymentTypeRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<PaymentType> list() {
		return entityManager.createQuery("from PaymentType", PaymentType.class).getResultList();
	}
	
	@Override
	public PaymentType getById(Long id) {
		return entityManager.find(PaymentType.class, id);
	}
	
	@Transactional
	@Override
	public PaymentType save(PaymentType paymentType) {
		return entityManager.merge(paymentType);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		PaymentType paymentType = getById(id);
		
		if (paymentType == null) throw new EmptyResultDataAccessException(1);
		
		entityManager.remove(paymentType);
	}

}
