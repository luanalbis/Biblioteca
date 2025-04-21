<%@page import="model.relatory.AutorRelatory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.relatory.*"%>
<%@ page import="model.entity.Usuario"%>
<%@ page import="model.entity.Livro"%>
<%@ page import="java.util.ArrayList"%>
<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
ArrayList<AutorRelatory> autorRelatorio = (ArrayList<AutorRelatory>) request.getAttribute("autorRelatorio");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta charset="UTF-8">
<title>Relatório</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="estilos/style.css">
</head>
<body>
	<div class="cabecalhoContainer">
		<div class="usuarioInfo">
			<div class=fotoeNomePerfilContainer>
				<div class="fotoPerfilMenuContainer">
					<a href="perfil" class="fotoPerfilMenu"><img
						alt="foto de perfil" src="fotoUsuario?id=<%=usuario.getIdusuario()%>"></a>
				</div>
				<a href="perfil" class="usuarioNome"><%=usuario.getNome()%></a>
			</div>
			<button id="btnMenuMobile" class="menuToggle">&#9776;</button>
		</div>


		<ul id="menuDropdown" class="menuDropdown">
			<li><a href="main">Inicio</a></li>
			<li><a href="perfil">Perfil</a></li>
			<li><a href="main">Relatório</a></li>
			<li><a href="logout">Sair</a></li>
		</ul>
	</div>
	<h1>Relatório</h1>


	<!-- Opções de filtro que são exibidas/ocultadas -->


	<div class="tabelaDetalhesConteiner"></div>
	<table class="tabelaDetalhes">
		<div class="filtroWrapper">
			<button id="btnFiltro" class="botaoFiltroToggle">Filtrar ⬇</button>
			<div id="filtroContainer" style="display: none;">
				<a href="relatorio?filter=autor" class="filtroBotao">Autor</a> <a
					href="relatorio?filter=genero" class="filtroBotao">Gênero</a> <a
					href="relatorio?filter=lidos" class="filtroBotao">Lidos</a> <a
					href="relatorio?filter=nao_lidos" class="filtroBotao">Não Lidos</a>
			</div>

			<thead>
				<tr>
					<th>N</th>
					<th>Autor</th>
					<th>Lidos</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<%
				int numeracao = 1;
				for (AutorRelatory a : autorRelatorio) {
				%>
				<tr>
					<td><%=numeracao++%></td>
					<td><%=a.getAutor()%></td>
					<td><%=a.getEstado()%></td>
					<td><%=a.getTotal()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</div>
	</table>


	<div class="BotaoVoltarContainer">
		<a href="main" class="Botao1">Voltar</a>
	</div>
	<script src="scripts/menu.js"></script>
	<script src="scripts/filtro.js"></script>
</body>
</html>