<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entity.Usuario"%>
<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
if (usuario == null) {
	usuario = (Usuario) session.getAttribute("usuarioLogado");
}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar Perfil</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="estilos/style.css">
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
	<h1>Editar Perfil</h1>
	<form name="frmEditarPerfil" action="verificarDados" method="POST"
		enctype="multipart/form-data">

		<div class="editarperfilConteiner">


			<div class="perfil-foto-container">
				<img class="perfil-foto"
					src="fotoUsuario?id=<%=usuario.getIdusuario()%>"
					alt="Foto de perfil" id="previewFoto"
					onerror="this.onerror=null;this.src='imagens/user.png';">

				<!-- Campo oculto para indicar remoção -->

			</div>
			<div class="botaoEditarFotoConteiner">
				<!-- Input escondido -->
				<input type="file" name="fotoPerfil" id="inputFotoPerfil"
					class="inputFileHidden" accept="image/*">

				<!-- Label estilizado como botão -->
				<label for="inputFotoPerfil" class="botaoEditarFoto">Editar
					foto</label>

				<!-- Campo oculto da remoção -->
				<input type="hidden" name="removerFoto" id="removerFoto"
					value="false">

				<!-- Botão normal de remover -->
				<button type="button" class="botaoEditarFoto"
					onclick="removerFotoPerfil()">Remover foto</button>
			</div>

		</div>
		<div class="perfil-info">
			<input name="idusuario" type="hidden"
				value="<%=usuario.getIdusuario()%>"> <input name="nome"
				class="Caixa1" type="text" value="<%=usuario.getNome()%>"> <input
				name="email" class="Caixa1" type="email"
				value="<%=usuario.getEmail()%>">
			<div class="senhaWrapper">
				<input minlegth="8" name="senha" class="Caixa1" type="password"
					placeholder="Nova Senha"> <img class="BotaoOlho"
					src="imagens/view.png" alt="Mostrar senha" title="Mostrar senha"
					onclick="mostrarSenha(this)">
			</div>
		</div>

		<div class="botoesEditarPerfilConteiner">
			<input onclick="validarEditarPerfil()" class="botoesEditarPerfil"
				type="button" value="Salvar"> <a href="perfil"
				class="botoesEditarPerfil">Voltar</a>
		</div>
		<%
		if ("enviado".equals(request.getParameter("statusEmail"))) {
		%>
		<a href="main" class="mensagemEmailEnviado"> Verifique seu email
			para confirmar edição</a>
		<%
		}
		%>
		</div>
	</form>
	<script src="scripts/menu.js"></script>
	<script src="scripts/validador.js"></script>
	<script src="scripts/fotoperfil.js"></script>


</body>
</html>
