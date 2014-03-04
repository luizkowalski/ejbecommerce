package br.com.luizkowalski.ejb;

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
	
	private ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
	
	@Override
	public List<Produto> listarProdutosAtivos() {
		return produtoDAO.findAll();
	}

	@Override
	public void salvar(Produto produto) {
		produtoDAO.save(produto);
	}

	@Override
	public Produto buscarCodigo(String codigo) {
		return produtoDAO.findByCodigo(codigo);
	}

}
