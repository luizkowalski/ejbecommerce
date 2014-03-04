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
		Produto produto = new Produto("Celular","iPhone 5S","IP5123","resources/img/produtos/5s.png",
				new BigDecimal(1500),new BigDecimal(200), new BigDecimal(30));
		
		Produto produtoMac = new Produto("Notebook","MacBook Pro","MBPRO123","resources/img/produtos/mac.png",
				new BigDecimal(3000),new BigDecimal(200), new BigDecimal(30));
		
		produtoService.salvar(produto);
		produtoService.salvar(produtoMac);
	}
	
	public void doIt(){
		// TODO
	}
	
}
