<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entity.Usuario"%>
<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html >
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="estilos/style.css">
<title>Perfil</title>
</head>
<body>
	<div class="cabecalhoContainer">
		<div class="usuarioInfo">
			<div class=fotoeNomePerfilContainer>
				<div class="fotoPerfilMenuContainer">
					<a href="main" class="fotoPerfilMenu"><img alt="foto de perfil"
						src="fotoUsuario?id=<%=usuario.getIdusuario()%>"></a>
				</div>
				<a href="main" class="usuarioNome"><%=usuario.getNome()%></a>
			</div>
			<button id="btnMenuMobile" class="menuToggle">&#9776;</button>
		</div>

		<ul id="menuDropdown" class="menuDropdown">
			<li><a href="main">Inicio</a></li>
			<li><a href="relatorio">Relatório</a></li>
			<li><a href="logout">Sair</a></li>
		</ul>
	</div>

	<div class="perfil-wrapper">
		<h1>Meu Perfil</h1>
		<div class="perfil-foto-container">
			<img class="perfil-foto"
				src="fotoUsuario?id=<%=usuario.getIdusuario()%>"
				alt="Foto de perfil">
		</div>

		<div class="perfil-info">
			<input name="idusuario" type="hidden"
				value="<%=usuario.getIdusuario()%>">
			<p><%=usuario.getNome()%></p>
			<p>
				<%=usuario.getEmail()%></p>
		</div>

		<a href="editarPerfil" class="btn-editar">Editar Dados</a>
		<%
		if ("atualizado".equals(request.getParameter("statusDados"))) {
		%>
		<a class="mensagemEmailEnviado">Dados Atualizados!</a>
		<%
		} else if ("true".equals(request.getParameter("loginExiste"))) {
		%>
		<a class="mensagemEmailEnviado">Email digitado já possui login</a>
		<%
		}
		%>
	</div>
	<script src="scripts/menu.js"></script>

</body>
</html>