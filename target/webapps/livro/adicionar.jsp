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
<title>Adicionar livros</title>
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
	<h1>Adicionar livro</h1>
	<form name="frmAdicionar" action="create">
		<div class="adicionarLivroContainer">
			<table>

				<tr>
					<td><input name="titulo" type="text" class="Caixa1"
						placeholder="Título"></td>
				</tr>
				<tr>
					<td><input name="autor" type="text" class="Caixa1"
						placeholder="Autor"></td>
				</tr>
				<tr>
					<td><select name="genero" class="Caixa1 selectCustom" required>
							<option value="">Selecione o gênero</option>
							<option value="Aventura">Aventura</option>
							<option value="Romance">Romance</option>
							<option value="Romance">Dark Romance</option>
							<option value="Ficção">Ficção</option>
							<option value="Terror">Terror</option>
							<option value="Drama">Drama</option>
							<option value="Biografia">Biografia</option>
							<option value="Fantasia">Fantasia</option>
							<option value="Outros">Outros</option>
					</select></td>
				</tr>
				<tr>
					<td><select name="estado" class="Caixa1 selectCustom" required>
							<option value="">Selecione o estado</option>
							<option value="Lido">Lido</option>
							<option value="Não Lido">Não Lido</option>
					</select></td>
				</tr>
				<tr>
					<td><textarea name="descricao" class="CaixaDescricao"
							placeholder="Descrição"></textarea></td>
				</tr>

			</table>
			<div class="BotoesAdicionarContainer">
				<input type="button" class="Botao1" value="Adicionar"
					onclick="validador()"> <a href="main" class="Botao1">Voltar</a>
			</div>
		</div>
	</form>
	<script src="scripts/validador.js"></script>
	<script src="scripts/menu.js"></script>



</body>
</html>