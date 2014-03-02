package br.com.luizkowalski.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.luizkowalski.ejb.remote.ProdutoRemote;

@Startup
@Singleton(name="setupBean")
public class SetupBean {

	@EJB
	private ProdutoRemote produtoService;
	
	@PostConstruct
	public void setupDatabase(){
		// Instanciar os produtos para a venda
	}
	
}
