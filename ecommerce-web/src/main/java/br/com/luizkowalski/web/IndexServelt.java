package br.com.luizkowalski.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.luizkowalski.ejb.SetupBean;
import br.com.luizkowalski.ejb.remote.ProdutoRemote;
import br.com.luizkowalski.persistence.entities.Produto;

@WebServlet(urlPatterns= "/listaProdutos")
public class IndexServelt extends HttpServlet {
	private static final long serialVersionUID = 5757804319502202759L;

	@EJB
	private SetupBean setup;
	
	@EJB
	private ProdutoRemote produtoBean;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setup.doIt();
		BigDecimal despesasTotais = BigDecimal.ZERO;
		List<Produto> produtosAtivos = produtoBean.listarProdutosAtivos();
		
		for (Produto produto : produtosAtivos) {
			despesasTotais = despesasTotais.add(produto.getCustoCompra());
		}
		BigDecimal rateioDespesas = despesasTotais.divide(new BigDecimal(produtosAtivos.size()));
		
		for (Produto produto : produtosAtivos) {
			produto.calcularValorVenda(rateioDespesas);
		}
		
		req.setAttribute("produtos", produtosAtivos);
		getServletContext().getRequestDispatcher("/listaProdutos.jsp").forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		
		String codigo = req.getParameter("codigoInterno");
		Integer quantidade = Integer.parseInt(req.getParameter("quantidade"));
		BigDecimal valor = new BigDecimal(req.getParameter("preco"));
		
		session.setAttribute(codigo, Arrays.asList(quantidade, valor));
		res.sendRedirect("carro-compra");
	}

}
