<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="produto" items="${produtos}" varStatus="i">
	<div class="col-xs-4 produto">
		<h2>${produto.nome}${produto.codigoInterno}</h2>
		<p>${produto.descricao}</p>
		<h4>${produto.valor}</h4>
		<form action="produtos" method="post">
			<input type="hidden" value="${produto.codigoInterno}" name="codigo">
			<button type="submit" class="btn btn-primary">Adicionar ao
				carrinho</button>
		</form>
	</div>
	<c:if test="${(i.index + 1) % 3 == 0}">
		</div>
		<div class="row">
	</c:if>
</c:forEach>