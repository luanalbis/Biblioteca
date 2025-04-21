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
import model.dao.DAOrelatory;
import model.entity.Livro;
import model.entity.Usuario;
import model.relatory.MainRelatory;
import utils.ControllerUtil;

@WebServlet("/relatorio")
public class RelatoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOlivro daoLivro = new DAOlivro();
	DAOrelatory daoRelatory = new DAOrelatory();

	public RelatoryController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/relatorio")) {
			redirecionarRelatorio(request, response);
		}
	}

	private void redirecionarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (!ControllerUtil.verificarSessao(request, response)) {
			return;
		}
		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);
		ArrayList<Livro> listaDeLivros = daoLivro.mostrarLivros(usuario.getIdusuario());
		MainRelatory relatorioCompleto = daoRelatory.mainRelatory(listaDeLivros);

		request.setAttribute("usuario", usuario);
		request.setAttribute("relatorioCompleto", relatorioCompleto);

		String filter = request.getParameter("filter");

		RequestDispatcher rd = null;

		if ("genero".equals(filter)) {
			request.setAttribute("generoRelatorio", relatorioCompleto.getRelatorioGenero());
			rd = request.getRequestDispatcher("livro/relatorio/relatoriogenero.jsp");
		} else if ("lidos".equals(filter)) {
			request.setAttribute("lidosRelatorio", relatorioCompleto.getRelatorioLidos());
			rd = request.getRequestDispatcher("livro/relatorio/relatoriolidos.jsp");
		} else if ("nao_lidos".equals(filter)) {
			request.setAttribute("naoLidosRelatorio", relatorioCompleto.getRelatorioNaoLidos());
			rd = request.getRequestDispatcher("livro/relatorio/relatorionaolidos.jsp");
		} else {
			request.setAttribute("autorRelatorio", relatorioCompleto.getRelatorioAutor());
			rd = request.getRequestDispatcher("livro/relatorio/relatorioautor.jsp");
		}

		rd.forward(request, response);

	}

}
