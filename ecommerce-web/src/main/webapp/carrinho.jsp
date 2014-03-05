<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:base>
	<div class="row">
		<div class="large-12 columns">
			<table>
				<thead>
					<tr>
						<th width="200">Codigo</th>
						<th>Nome</th>
						<th>Foto</th>
						<th>Descrição</th>
						<th>Quantidade</th>
						<th>Valor</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="produto" items="${produtosCarrinho}" varStatus="i">
						<tr>
							<td>${produto.codigoInterno}</td>
							<td>${produto.nome}</td>
							<td><a class="th" href="${produto.caminhoFoto}"> <img
									src="${produto.caminhoFoto}">
							</a></td>
							<td>${produto.descricao}</td>
							<td>
								<form action="carro-compra" method="post">
									<div class="large-5 columns">
										<input type="text" name="quantidade" value="${produto.quantidade}" />
										<input type="hidden" value="${produto.codigoInterno}" name="codigoInterno">
										<input type="hidden" value="${produto.valorVenda}" name="preco">
									</div>
								</form>
							</td>
							<td><fmt:formatNumber pattern="#,##0"
									value="${produto.valorVenda}" type="currency" /></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5"></td>
						<td><b>Valor total dos produtos: ${valorTotal}</b></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</t:base>
