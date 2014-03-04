<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<!DOCTYPE html>
<!--[if IE 9]><html class="lt-ie10" lang="en" > <![endif]-->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>myCommerce</title>

<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/foundation.css">

<script src="resources/js/vendor/modernizr.js"></script>

</head>
<body>

	<div id="bodyPage">
		<div class="row">
			<div class="large-12 columns">
				<div class="panel">
					<h3><a href="/ecommerce">Bem vindo ao myCommerce</a></h3>
					<p>Escolha os produtos, adicione ao carrinho e boas compras</p>
				</div>
			</div>
		</div>

		<jsp:doBody />
	</div>

	<script src="resources/js/vendor/jquery.js"></script>
	<script src="resources/js/foundation.min.js"></script>
	<script>
    $(document).foundation();
  </script>
</body>
</html>