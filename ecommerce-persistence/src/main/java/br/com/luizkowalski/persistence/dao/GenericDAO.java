package br.com.luizkowalski.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class GenericDAO<EntityType, PrimaryKeyType extends Serializable> {

	private EntityManager entityManager;
	
	public GenericDAO(EntityManager em) {
		this.entityManager = em;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void save(EntityType newEntity) {
        entityManager.persist(newEntity);
    }

    public void update(EntityType entity) {
        entityManager.merge(entity);
    }

    public abstract EntityType find(PrimaryKeyType primaryKey);
    public abstract List<EntityType> findAll();

    public void delete(EntityType entity) {
    	entityManager.remove(entity);
    }
}
