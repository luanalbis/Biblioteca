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
<meta charset="UTF-8">
<title>Editar Livro</title>
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
	<h1>Atualizar Livro</h1>
	<form name="frmAdicionar" action="update">
	<div class="adicionarLivroContainer">
		<table>
			<tr>
				<td><input class="Caixa1" type="hidden" name="idcadastro"
					value="<%=livro.getIdcadastro()%>"></td>
			</tr>
			<tr>
				<td><input class="Caixa1" type="text" name="titulo"
					value="<%=livro.getTitulo()%>"></td>
			</tr>
			<tr>
				<td><input class="Caixa1" type="text" name="autor"
					value="<%=livro.getAutor()%>"></td>
			</tr>
			<tr>
				<td><select id="caixaOption" class="Caixa1" name="genero"
					required>
						<option value="">Selecione o gênero</option>
						<option value="Aventura"
							<%=livro.getGenero().equals("Aventura") ? "selected" : ""%>>Aventura</option>
						<option value="Romance"
							<%=livro.getGenero().equals("Romance") ? "selected" : ""%>>Romance</option>
						<option value="Ficção"
							<%=livro.getGenero().equals("Ficção") ? "selected" : ""%>>Ficção</option>
						<option value="Terror"
							<%=livro.getGenero().equals("Terror") ? "selected" : ""%>>Terror</option>
						<option value="Drama"
							<%=livro.getGenero().equals("Drama") ? "selected" : ""%>>Drama</option>
						<option value="Biografia"
							<%=livro.getGenero().equals("Biografia") ? "selected" : ""%>>Biografia</option>
						<option value="Fantasia"
							<%=livro.getGenero().equals("Fantasia") ? "selected" : ""%>>Fantasia</option>
						<option value="Outros"
							<%=livro.getGenero().equals("Outros") ? "selected" : ""%>>Outros</option>
				</select></td>
			</tr>

			<tr>
				<td><select class="Caixa1" id="caixaOption" name="estado"
					required>
						<option value="">Selecione o estado</option>
						<option value="Lido"
							<%=livro.getEstado().equals("Lido") ? "selected" : ""%>>Lido</option>
						<option value="Não Lido"
							<%=livro.getEstado().equals("Não Lido") ? "selected" : ""%>>Não
							Lido</option>
				</select></td>
			</tr>

			<tr>
				<td><textarea class="CaixaDescricao" name="descricao"><%=livro.getDescricao()%></textarea>
				</td>
			</tr>
		</table>
		<div class="BotoesAtualizarContainer">
			<input type="button" class="BotaoAtualizar" value="Salvar"
				onclick="validador()"> <a href="main" class="BotaoAtualizar">Voltar</a>
		</div>
		</div>
	</form>
	<script src="scripts/validador.js"></script>
	<script src="scripts/menu.js"></script>

</body>
</html>