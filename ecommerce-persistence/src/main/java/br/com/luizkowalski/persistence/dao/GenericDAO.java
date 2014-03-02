package br.com.luizkowalski.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO<EntityType, PrimaryKeyType extends Serializable> {

	@PersistenceContext(unitName = "ecommerce")
	private EntityManager entityManager;
	
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
