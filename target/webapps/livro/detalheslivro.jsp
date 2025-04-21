<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entity.Usuario"%>
<%@ page import="model.entity.Livro"%>
<%
Livro livro = (Livro) request.getAttribute("livro");
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta charset="UTF-8">
<title>Detalhes do Livro</title>
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
			<li><a href="relatorio">Relatório</a></li>
			<li><a href="logout">Sair</a></li>
		</ul>
	</div>
	<h1>Detalhes do Livro</h1>

	<table class="tabelaDetalhes">

		<tr>
			<th>Título</th>
			<td><%=livro.getTitulo()%></td>
		</tr>
		<tr>
			<th>Autor</th>
			<td><%=livro.getAutor()%></td>
		</tr>
		<tr>
			<th>Gênero</th>
			<td><%=livro.getGenero()%></td>
		</tr>
		<tr>
			<th>Status</th>
			<td><%=livro.getEstado()%></td>
		<tr>
			<th>Descrição</th>
			<td><textarea class="CaixaDescricao" readonly
					oninput="autoExpand(this)"><%=livro.getDescricao()%></textarea>
			</td>
		</tr>
	</table>

	<div class="BotaoVoltarContainer">
		<a href="main" class="Botao1">Voltar</a>
	</div>
	<script src="scripts/menu.js"></script>
</body>
</html>