<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.entity.Livro"%>
<%@ page import="model.entity.Usuario"%>
<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<%
ArrayList<Livro> lista = (ArrayList<Livro>) request.getAttribute("livros");
%>


<!DOCTYPE html >
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="estilos/style.css">
<title>Meus Livros</title>
</head>
<body>
	<div class="cabecalhoContainer">
		<div class="usuarioInfo">
			<div class=fotoeNomePerfilContainer>
				<div class="fotoPerfilMenuContainer">
					<a href="perfil" class="fotoPerfilMenu"><img
						alt="foto de perfil"
						src="fotoUsuario?id=<%=usuario.getIdusuario()%>"></a>
				</div>
				<a href="perfil" class="usuarioNome"><%=usuario.getNome()%></a>
			</div>
			<button id="btnMenuMobile" class="menuToggle">&#9776;</button>
		</div>

		<ul id="menuDropdown" class="menuDropdown">
			<li><a href="perfil">Perfil</a></li>
			<li><a href="relatorio">Relatório</a></li>
			<li><a href="logout">Sair</a></li>
		</ul>
	</div>


	<h1>Meus Livros</h1>
	<a class="Botao1" href="add">Adicionar</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>N</th>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Gênero</th>
				<th>Status</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			int numeracao = 1;
			for (Livro l : lista) {
			%>
			<tr>

				<td><%=numeracao++%></td>
				<td><%=l.getTitulo()%></td>
				<td><%=l.getAutor()%></td>
				<td><%=l.getGenero()%></td>
				<td><%=l.getEstado()%></td>
				<td>
					<div class="OpcoesContainer">

						<a href="select?idcadastro=<%=l.getIdcadastro()%>"> <img
							class="BotaoFoto" src="imagens/edit.png" alt="Editar"
							title="Editar">
						</a> <a href="javascript:confirmar(<%=l.getIdcadastro()%>)"> <img
							class="BotaoFoto" src="imagens/delete.png" alt="Deletar"
							title="Deletar">
						</a> <a href="details?idcadastro=<%=l.getIdcadastro()%>"> <img
							class="BotaoFoto" src="imagens/more.png" alt="Mais"
							title="Mais detalhes">
						</a>
					</div>
				</td>

			</tr>
			<%}%>
		</tbody>
	</table>

	<script src="scripts/confirmador.js"></script>
	<script src="scripts/menu.js"></script>

</body>
</html>