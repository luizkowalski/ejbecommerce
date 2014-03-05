package br.com.luizkowalski.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.luizkowalski.ejb.remote.ProdutoRemote;
import br.com.luizkowalski.persistence.entities.Produto;

@WebServlet(urlPatterns="/carro-compra")
public class CarrinhoServlet extends HttpServlet{
	private static final long serialVersionUID = 5002397631732352871L;
	
	@EJB
	private ProdutoRemote produtoService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//Codigos dos produtos
		
		List<Produto> produtosCarrinho = new ArrayList<>();
		BigDecimal valorTotal = BigDecimal.ZERO;
		
		Enumeration<String> atributos = req.getSession().getAttributeNames();
		List<String> codigos = preencherCodigos(atributos);
		
		for (String codigo : codigos) {
			Produto p = produtoService.buscarCodigo(codigo);
			List quantidadeValor = (List) req.getSession().getAttribute(codigo);
			Integer quantidade = (Integer) quantidadeValor.get(0);
			p.setQuantidade(quantidade);
			BigDecimal valorVenda = (BigDecimal) quantidadeValor.get(1);
			valorTotal = valorTotal.add(valorVenda.multiply(new BigDecimal(quantidade)));
			p.setValorVenda(valorVenda);
			
			produtosCarrinho.add(p);
		}
		
		req.setAttribute("produtosCarrinho", produtosCarrinho);
		req.setAttribute("valorTotal", valorTotal);
		
		// dispatch relativo a raiz do servletcontext
		getServletContext().getRequestDispatcher("/carrinho.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String codigoProdutoAlterado = req.getParameter("codigoInterno");
		Integer quantidadeNova = Integer.parseInt(req.getParameter("quantidade"));
		BigDecimal valor = new BigDecimal(req.getParameter("preco"));
		
		HttpSession session = req.getSession();
		Enumeration<String> atributos = req.getSession().getAttributeNames();
		List<String> codigos = preencherCodigos(atributos);
		
		for (String codigo : codigos) {
			if(codigo.equals(codigoProdutoAlterado)){
				session.removeAttribute(codigo);
				session.setAttribute(codigo, Arrays.asList(quantidadeNova, valor));
			}
		}
		
		//getServletContext().getRequestDispatcher("/carrinho.jsp").forward(req, res);
		res.sendRedirect("carro-compra");
	}
	
	private List<String> preencherCodigos(Enumeration<String> atributos) {
		List<String> codigos = new ArrayList<>();
		while(atributos.hasMoreElements()){
			String att = atributos.nextElement();
			if(att.startsWith("AA")){ // Todos os produtos começam com AA
				codigos.add(att);
			}
		}
		return codigos;
	}

}
