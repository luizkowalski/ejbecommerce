package br.com.luizkowalski.ejb.remote;

import java.math.BigDecimal;
import java.util.List;

import br.com.luizkowalski.persistence.entities.Produto;

public interface ProdutoRemote {

	public List<Produto> listarProdutosAtivos();
	
	public void salvar(Produto produto);
	
	public Produto buscarCodigo(String codigo);
	
	public BigDecimal calcularValorVenda(Produto p);
}
