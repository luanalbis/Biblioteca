<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cadastro</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/estilos/style.css">

</head>
<body>
	<div class="cadastroContainer">
		<h1>Cadastro</h1>
		<form action="../register" method="POST">
			<input class="Caixa1" type="text" name="nome" placeholder="Nome"
				required pattern="[A-Za-zÁ-ú ]+"
				title="O nome deve conter apenas letras."> <input
				class="Caixa1" type="email" name="email" placeholder="E-mail"
				required> <input class="Caixa1" type="password" name="senha"
				placeholder="Senha" required minlength="8"
				title="A senha deve ter no mínimo 8 caracteres.">
			<div class=" BotoesPaginaCadastroContainer">

				<input type="submit" class="BotaoPaginaCadastro" value="Confirmar">
				<a href="../index.jsp" class="BotaoPaginaCadastro">Voltar</a>
			</div>

		</form>

	</div>
	<%
	if ("loginJaExiste".equals(request.getParameter("erro"))) {
	%>
	<a class="mensagemSenhaIncorreta" href="reset"> Já possui
		cadastro. Esqueceu a senha?</a>
	<%
	} else if ("enviado".equals(request.getParameter("emailStatus"))) {
	%>
	<a class="mensagemEmailEnviado" href="../index.jsp">Email de confirmação enviado para seu email. </a>
	<%
	} else if ("erroDeCadastro".equals(request.getParameter("ConfirmErro"))){%>
	<a class="mensagemSenhaIncorreta" href="cadastro.jsp"> Erro ao realizar cadastro</a>
	<% }else if ("tokenInvalido".equals(request.getParameter("ConfirmErro"))){%>
	<a class="mensagemSenhaIncorreta" href="cadastro.jsp"> Link de confirmação Inválido</a>
	<% }%>
</body>
</html>
