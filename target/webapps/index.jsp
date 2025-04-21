<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Biblioteca</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="estilos/style.css">
</head>
<body>
	<h1>Biblioteca</h1>
	<div class="inicioContainer">
		<img class="ImgInicio" alt="imagen livraria" src="imagens/library.png">

		<form name="frmLogin" action="login" method="post">
			<div class="loginContainer">
				<input maxlength="40" name="login" class="Caixa1" type="email"
					placeholder="Login">

				<div class="senhaWrapper">
					<input name="senha" maxlength="30" class="Caixa1 senhaInput"
						type="password" placeholder="Senha"> <img
						class="BotaoOlho" src="imagens/view.png" alt="Mostrar senha"
						title="Mostrar senha" onclick="mostrarSenha(this)">
				</div>
			</div>

			<div class="botaoLoginContainer">
				<input type="button" class="Botao1" value="Entrar"
					onclick="validarLogin()"> <a href="usuario/cadastro.jsp"
					class="Botao1">Cadastrar</a>
			</div>

			<%
			if ("inexistente".equals(request.getAttribute("loginInexistente"))) {
			%>
			<p class="mensagenLoginInexistente">Login não existe!</p>
			<%
			} else if ("incorreta".equals(request.getAttribute("senhaIncorreta"))) {
			String login = (String) request.getAttribute("login");
			%>

			<a class="mensagemSenhaIncorreta" href="reset?login=<%=login%>">Senha
				incorreta. Esqueceu a senha?</a>
			<%
			} else if ("enviado".equals(request.getParameter("statusEmail"))) {
			%>
			<p class="mensagemEmailEnviado">
				Email de redefinição enviado para seu email!
				<%
			} else if ("tokenInvalido".equals(request.getParameter("ConfirmErro"))) {
			%>
				<a class="mensagemSenhaIncorreta" href="index.jsp"> Token
					Expirado.Tente novamente</a>
				<%
				} else if ("atualizada".equals(request.getParameter("statusSenha"))) {
				%>
				<a class="mensagemSenhaIncorreta" href="index.jsp"> Senha
					Atualizada, faça seu Login.</a>
				<%}%>
			
		</form>
	</div>


	<script src="scripts/validador.js"></script>
</body>
</html>