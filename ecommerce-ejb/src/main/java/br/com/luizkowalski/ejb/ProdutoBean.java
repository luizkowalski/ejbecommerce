package br.com.luizkowalski.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.luizkowalski.ejb.remote.ProdutoRemote;
import br.com.luizkowalski.persistence.dao.ProdutoDAO;
import br.com.luizkowalski.persistence.entities.Produto;

@Stateless(name="produtoBean")
@Remote(ProdutoRemote.class)
public class ProdutoBean implements ProdutoRemote {

	@PersistenceContext(unitName = "ecommerce")
	private EntityManager entityManager;
	
	private ProdutoDAO produtoDAO;
	
	@Override
	public List<Produto> listarProdutosAtivos() {
		return instanciarDAO().findAll();
	}

	@Override
	public void salvar(Produto produto) {
		instanciarDAO().save(produto);
	}

	@Override
	public Produto buscarCodigo(String codigo) {
		return instanciarDAO().findByCodigo(codigo);
	}
	
	private ProdutoDAO instanciarDAO(){
		if(produtoDAO == null)
			produtoDAO = new ProdutoDAO(entityManager);
		
		return produtoDAO;
	}

	@Override
	public BigDecimal calcularValorVenda(Produto p) {
		return null;
	}

}
