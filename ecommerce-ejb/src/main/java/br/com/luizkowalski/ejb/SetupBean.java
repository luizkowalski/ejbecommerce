package br.com.luizkowalski.ejb;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.luizkowalski.ejb.remote.ProdutoRemote;
import br.com.luizkowalski.persistence.entities.Produto;

@Startup
@Singleton(name="setupBean")
public class SetupBean {

	@EJB
	private ProdutoRemote produtoService;
	
	@PostConstruct
	public void setupDatabase(){
		// Instanciar os produtos para a venda
		Produto produto = new Produto("Celular","iPhone 5S Prata","AAIP5123","resources/img/produtos/5s.png",
				new BigDecimal(100),new BigDecimal(200), new BigDecimal(10));
		
		Produto produtoGolden = new Produto("Celular","iPhone 5S Golden","AAIP5456","resources/img/produtos/5s.png",
				new BigDecimal(200),new BigDecimal(200), new BigDecimal(10));
		
		Produto produtoMac = new Produto("Notebook","MacBook Pro","AAMBPRO123","resources/img/produtos/mac.png",
				new BigDecimal(300),new BigDecimal(200), new BigDecimal(10));
		
		produtoService.salvar(produto);
		produtoService.salvar(produtoGolden);
		produtoService.salvar(produtoMac);
	}
	
	public void doIt(){
		// TODO
	}
	
}
