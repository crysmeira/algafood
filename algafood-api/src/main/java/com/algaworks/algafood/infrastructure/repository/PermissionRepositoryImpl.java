package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.PermissionRepository;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Permission> list() {
		return entityManager.createQuery("from Permission", Permission.class).getResultList();
	}
	
	@Override
	public Permission getById(Long id) {
		return entityManager.find(Permission.class, id);
	}
	
	@Transactional
	@Override
	public Permission save(Permission permission) {
		return entityManager.merge(permission);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		Permission permission = getById(id);
		
		if (permission == null) throw new EmptyResultDataAccessException(1);
		
		entityManager.remove(permission);
	}

}
