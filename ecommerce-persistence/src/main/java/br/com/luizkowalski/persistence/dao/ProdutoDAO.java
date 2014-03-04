package br.com.luizkowalski.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.luizkowalski.persistence.entities.Produto;

public class ProdutoDAO extends GenericDAO<Produto, Long> {

	public ProdutoDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<Produto> findAll() {
		return getEntityManager().
				createQuery("select p from Produto p where p.custoCompra is not null", Produto.class).getResultList();
	}

	@Override
	public Produto find(Long primaryKey) {
		return getEntityManager().find(Produto.class, primaryKey);
	}
	
	public Produto findByCodigo(String codigo){
		TypedQuery<Produto> query = 
				getEntityManager().createQuery("select p from Protudo p where p.codigo = :codigo", Produto.class);
		query.setParameter("codigo", codigo);
		
		return query.getSingleResult();
	}

}
