package br.com.luizkowalski.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.luizkowalski.ejb.ProdutoBean;
import br.com.luizkowalski.ejb.SetupBean;

@WebServlet(urlPatterns= {"/"})
public class IndexServelt extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private SetupBean setup;
	
	@EJB
	private ProdutoBean produtoBean;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setup.doIt();
		req.setAttribute("produtos", produtoBean.listarProdutosAtivos());
	}

}
