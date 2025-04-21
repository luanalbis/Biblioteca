package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DAOrelatory;
import model.dao.DAOlivro;
import model.dao.DAOusuario;
import model.entity.Livro;
import model.entity.Usuario;
import model.relatory.AutorRelatory;
import utils.ControllerUtil;

@WebServlet({ "/LivroController", "/create", "/select", "/delete", "/update", "/details" })
public class LivroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOlivro daoLivro = new DAOlivro();
	DAOusuario daoUsuario = new DAOusuario();
	DAOrelatory daoRelatory = new DAOrelatory();

	public LivroController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/create")) {
			adicionarLivro(request, response);
		} else if (action.equals("/select")) {
			listarLivro(request, response);

		} else if (action.equals("/delete")) {
			deletarLivro(request, response);
		} else if (action.equals("/update")) {
			atualizarLivro(request, response);
		} else if (action.equals("/details")) {
			listarLivro(request, response);
		}  else {
			response.sendRedirect("main");
		}

	}

	private String receberIdusuario(HttpServletRequest request) {
		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);
		String login = usuario.getEmail();
		String idusuario = daoUsuario.idDoUsuario(login);
		return idusuario;
	}

	private void adicionarLivro(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (!ControllerUtil.verificarSessao(request, response)) {
			return;
		}
		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);
		String idusuario = usuario.getIdusuario();

		Livro livro = new Livro();
		livro.setTitulo(request.getParameter("titulo"));
		livro.setAutor(request.getParameter("autor"));
		livro.setGenero(request.getParameter("genero"));
		livro.setEstado(request.getParameter("estado"));
		livro.setDescricao(request.getParameter("descricao"));
		livro.setIduser(idusuario);

		daoLivro.adicionarLivro(livro);
		response.sendRedirect("main");
	}

	private boolean listarLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ControllerUtil.verificarSessao(request, response)) {
			return false;
		}
		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);

		String action = request.getServletPath();
		String idcadastro = request.getParameter("idcadastro");
		String idusuario = usuario.getIdusuario();
		Livro livro = daoLivro.selecionarLivro(idcadastro, idusuario);
		if (livro == null) {
			response.sendRedirect("index.jsp");
			return false;
		}

		request.setAttribute("usuario", usuario);
		request.setAttribute("livro", livro);

		if (action.equals("/select")) {
			RequestDispatcher rd = request.getRequestDispatcher("livro/editarlivro.jsp");
			rd.forward(request, response);
		} else if (action.equals("/details")) {
			RequestDispatcher rd = request.getRequestDispatcher("livro/detalheslivro.jsp");
			rd.forward(request, response);
		}
		return true;

	}

	private void deletarLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!ControllerUtil.verificarSessao(request, response)) {
			return;
		}
		String idusuario = receberIdusuario(request);
		String idcadastro = request.getParameter("idcadastro");

		daoLivro.deletarLivro(idcadastro, idusuario);

		response.sendRedirect("main");
	}

	private void atualizarLivro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!ControllerUtil.verificarSessao(request, response)) {
			return;
		}
		String idusuario = receberIdusuario(request);
		Livro livro = new Livro();

		livro.setIdcadastro(request.getParameter("idcadastro"));
		livro.setTitulo(request.getParameter("titulo"));
		livro.setAutor(request.getParameter("autor"));
		livro.setGenero(request.getParameter("genero"));
		livro.setEstado(request.getParameter("estado"));
		livro.setDescricao(request.getParameter("descricao"));
		livro.setIduser(idusuario);

		daoLivro.atualizarLivro(livro);

		response.sendRedirect("main");
	}

	

}
