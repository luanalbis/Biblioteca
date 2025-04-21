package controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DAOusuario;
import model.entity.Usuario;

@WebServlet({ "/login", "/logout" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOusuario daoUsuario = new DAOusuario();

	public LoginController() {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if (action.equals("/login")) {
			try {
				verificarLogin(request, response);
			} catch (IOException | ServletException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if (action.equals("/logout")) {
			logout(request, response);
		}
	}

	private boolean verificarLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, MessagingException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		boolean existeLogin = daoUsuario.verificarLoginExiste(login);

		if (!existeLogin) {
			request.setAttribute("loginInexistente", "inexistente");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return false;
		}

		boolean senhaCorreta = daoUsuario.verificarSenhaCorreta(login, senha);

		if (!senhaCorreta) {
			request.setAttribute("login", login);
			request.setAttribute("senhaIncorreta", "incorreta");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return false;
		}
		Usuario usuario = daoUsuario.dadosUsuario(login);
		HttpSession session = request.getSession();
		session.setAttribute("usuarioLogado", usuario);
		response.sendRedirect("main");

		return true;

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect("index.jsp");
	}
}
