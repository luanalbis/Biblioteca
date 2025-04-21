<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entity.Usuario"%>
<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Redefinir senha</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/estilos/style.css">

</head>
<body>
	<div class="cadastroContainer">
		<h1>Redefinir senha</h1>
		<form name="frmNovaSenha" action="changepassword" method="POST">
			<div class="SenhaWrapper">
				<input type="hidden" name="token"
					value="<%=request.getParameter("token")%>"> <input
					readonly="readonly" class="Caixa1" type="text" name="nome"
					value="<%=usuario.getNome()%>" required pattern="[A-Za-zÁ-ú ]+"
					title="O nome deve conter apenas letras.">
			</div>
			<div class="SenhaWrapper">
				<input readonly="readonly" class="Caixa1" type="email" name="email"
					value="<%=usuario.getEmail()%>" required>
			</div>
			<div class="senhaWrapper">
				<input class="Caixa1 senhaInput" type="password" name="novaSenha1"
					id="novaSenha1" placeholder="Nova Senha" required minlength="8"
					title="A senha deve ter no mínimo 8 caracteres."> <img
					class="BotaoOlho" src="imagens/view.png" alt="Mostrar senha"
					title="Mostrar senha" onclick="mostrarSenha(this)">
			</div>
			<div class="senhaWrapper">
				<input class="Caixa1 senhaInput" type="password" name="novaSenha2"
					id="novaSenha2" placeholder="Repita a Senha" required minlength="8"
					title="A senha deve ter no mínimo 8 caracteres."> <img
					class="BotaoOlho" src="imagens/view.png" alt="Mostrar senha"
					title="Mostrar senha" onclick="mostrarSenha(this)">
			</div>
			<div class=" BotoesPaginaCadastroContainer">

				<input type="button" onclick="atualizarSenha()"
					class="BotaoPaginaCadastro" value="Confirmar"> <a
					href="index.jsp" class="BotaoPaginaCadastro">Voltar</a>
			</div>

		</form>

	</div>
	<script src="scripts/validador.js"></script>
</body>
</html>
