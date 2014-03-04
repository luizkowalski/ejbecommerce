package br.com.luizkowalski.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.luizkowalski.ejb.SetupBean;
import br.com.luizkowalski.ejb.remote.ProdutoRemote;

@WebServlet(urlPatterns= "/listaProdutos")
public class IndexServelt extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private SetupBean setup;
	
	@EJB
	private ProdutoRemote produtoBean;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setup.doIt();
		req.setAttribute("produtos", produtoBean.listarProdutosAtivos());
		getServletContext().getRequestDispatcher("/listaProdutos.jsp").forward(req, res);
	}

}
