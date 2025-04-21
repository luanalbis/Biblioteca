package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DAOlivro;
import model.entity.Livro;
import model.entity.Usuario;
import utils.ControllerUtil;

@WebServlet({"/main","/add"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOlivro daoLivro = new DAOlivro();

	public HomeController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			meusLivros(request, response);
		}else if (action.equals("/add")) {
			redirecionaAdd(request, response);
		}else {
			response.sendRedirect("index.jsp");
		}
		
	}

	private void redirecionaAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);
		request.setAttribute("usuario", usuario);
		RequestDispatcher rd = request.getRequestDispatcher("livro/adicionar.jsp");
		rd.forward(request, response);
		
	}

	private void meusLivros(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (!ControllerUtil.verificarSessao(request, response)) {
			return;
		}

		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);
		String idusuario = usuario.getIdusuario();
		// System.out.println(ControllerUtil.dadosUsuarioLogado(request));

		ArrayList<Livro> lista = daoLivro.mostrarLivros(idusuario);

		// encaminhar a lista ao documento agenda.jsp
		request.setAttribute("usuario", usuario);
		request.setAttribute("livros", lista);
		RequestDispatcher rd = request.getRequestDispatcher("meuslivros.jsp");
		rd.forward(request, response);
	}
}
