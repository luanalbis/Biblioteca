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
import model.dao.DAOusuarioPendente;
import model.entity.Usuario;
import utils.email.EmailConfirmUtil;
import utils.email.EmailResetUtil;
import utils.email.TokenUtil;
import utils.password.SenhaUtil;

@WebServlet({ "/register", "/confirm", "/reset", "/redefine", "/changepassword" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOusuario daoUsuario = new DAOusuario();
	DAOusuarioPendente daoUsuarioPendente = new DAOusuarioPendente();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/register")) {
			try {
				enviarConfirmacaoConta(request, response);
			} catch (IOException | ServletException | MessagingException e) {
				e.printStackTrace();
			}
		} else if (action.equals("/changepassword")) {
			atualizarSenha(response, request);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/confirm")) {
			confirmarConta(request, response);
		} else if (action.equals("/reset")) {
			try {
				enviarLinkDeRedefinicao(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("/redefine")) {
			confirmarConta(request, response);
		}
	}

	private boolean enviarConfirmacaoConta(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, MessagingException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = SenhaUtil.criarHashSenha(request.getParameter("senha"));
		String token = TokenUtil.gerarTokenUnico();
		
		if (daoUsuario.verificarLoginExiste(email)) {
			response.sendRedirect("usuario/cadastro.jsp?erro=loginJaExiste");
			return false;
		}

		

		if (daoUsuarioPendente.verificarUsuarioPendenteExiste(email)) {
			daoUsuarioPendente.atualizarDados(nome, email, senha, token);

		} else {
			daoUsuarioPendente.registrarUsuarioPendente(nome, email, senha, token);
		}

		EmailConfirmUtil.enviarEmailConfirmacao(email, nome, token);

		response.sendRedirect("usuario/cadastro.jsp?emailStatus=enviado");
		return true;
	}

	private boolean confirmarConta(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getServletPath();
		String email = request.getParameter("email");
		String token = request.getParameter("token");

		Usuario novoUsuario = daoUsuarioPendente.verificarToken(email, token);
		System.out.println(novoUsuario);

		if (novoUsuario != null) {
			// atualização de dados
			if (daoUsuario.verificarLoginExiste(email)) {
				request.setAttribute("usuario", novoUsuario);
				RequestDispatcher rd = request.getRequestDispatcher("usuario/redefinir.jsp");
				rd.forward(request, response);
				return true;
			}

			// cadastro novo usuario
			if (daoUsuario.adicionarNovoUsuario(novoUsuario)) {
				daoUsuarioPendente.apagarUsuarioPendente(email, token);
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogado", daoUsuario.dadosUsuario(email));

				System.out.println("usuario cadastrado");
				response.sendRedirect("main");
				return true;
			} else {
				response.sendRedirect("usuario/cadastro.jsp?ConfirmErro=erroDeCadastro");
				return false;
			}
		} else {
			if (action.equals("/confirm"))
				response.sendRedirect("usuario/cadastro.jsp?ConfirmErro=tokenInvalido");
			else if (action.equals("/redefine")) {
				response.sendRedirect("index.jsp?ConfirmErro=tokenInvalido");
			}
			return false;
		}
	}

	private void enviarLinkDeRedefinicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		String login = request.getParameter("login");
		String token = TokenUtil.gerarTokenUnico();
		Usuario usuario = new Usuario();
		usuario = daoUsuario.dadosUsuario(login);

		// a senha está sendo usada apenas como campo auxiliar
		if (daoUsuarioPendente.verificarUsuarioPendenteExiste(login)) {
			daoUsuarioPendente.atualizarDados(usuario.getNome(), login, token, token);

		} else {
			daoUsuarioPendente.registrarUsuarioPendente(usuario.getNome(), login, token, token);
		}
		EmailResetUtil.enviarEmailRedefinicao(login, usuario.getNome(), token);
		response.sendRedirect("index.jsp?statusEmail=enviado");

	}

	private void atualizarSenha(HttpServletResponse response, HttpServletRequest request) throws IOException {
		Usuario usuario = new Usuario();
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		String senha = request.getParameter("novaSenha1");

		// esse método retorna o usuário sem a senha
		usuario = daoUsuario.dadosUsuario(email);
		usuario.setSenha(SenhaUtil.criarHashSenha(senha));
		daoUsuario.atualizarUsuario(usuario, request.getServletPath());
		daoUsuarioPendente.apagarUsuarioPendente(email, token);
		response.sendRedirect("index.jsp?statusSenha=atualizada");

	}

//	private void encaminharUsuario(HttpServletRequest request, HttpServletResponse response) {
//		String email = request.getParameter("email");
//		String token = request.getParameter("token");
//		Usuario usuario = new Usuario(); 
//		usuario = daoUsuarioPendente.verificarToken(email, token);
//		if (usuario != null) {
//			
//		}
//	}

}
