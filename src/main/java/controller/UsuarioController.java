package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.dao.DAOusuario;
import model.dao.DAOusuarioPendente;
import model.entity.Usuario;
import utils.ControllerUtil;
import utils.email.EmailAtualizarUtil;
import utils.email.TokenUtil;
import utils.password.SenhaUtil;

@WebServlet({ "/perfil", "/editarPerfil", "/verificarDados", "/atualizarDados" })
@MultipartConfig(maxFileSize = 10 * 1024 * 1024, // 10 MB
		maxRequestSize = 20 * 1024 * 1024)
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOusuario daoUsuario = new DAOusuario();
	DAOusuarioPendente daoUsuarioPendente = new DAOusuarioPendente();

	public UsuarioController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/perfil")) {
			mostrarPerfil(request, response);
		} else if (request.getServletPath().equals("/editarPerfil")) {
			mostrarPerfil(request, response);
		} else if (request.getServletPath().equals("/atualizarDados")) {
			atualizarDados(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			verificarDados(request, response);

		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
	}

	private void mostrarPerfil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ControllerUtil.verificarSessao(request, response)) {
			return;
		}

		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);
		request.setAttribute("usuario", usuario);
		if (request.getServletPath().equals("/perfil")) {
			RequestDispatcher rd = request.getRequestDispatcher("usuario/perfil.jsp");
			rd.forward(request, response);
		} else if (request.getServletPath().equals("/editarPerfil")) {
			RequestDispatcher rd = request.getRequestDispatcher("usuario/editarperfil.jsp");
			rd.forward(request, response);
		}
	}

	private void verificarDados(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, ServletException {
		if (!ControllerUtil.verificarSessao(request, response)) {
			return;
		}

		Usuario usuario = ControllerUtil.obterUsuarioLogado(request);
		String email = request.getParameter("email");

		if (!usuario.getEmail().equals(email)) {
			if (daoUsuario.verificarLoginExiste(email)) {
				response.sendRedirect("perfil?loginExiste=true");
				return;
			}

		}

		String idusuario = request.getParameter("idusuario");
		String nome = request.getParameter("nome");
		String senhaDigitada = request.getParameter("senha");
		String senha = verificarSeMudouSenha(senhaDigitada, idusuario);
		byte[] fotoPerfil = null;
		boolean removerFoto = "true".equals(request.getParameter("removerFoto"));

		if (!removerFoto) {
			fotoPerfil = capturarFotoDePerfil(request);
		}

		Usuario usuarioNovo = new Usuario(idusuario, nome, email, senha, fotoPerfil);

		if (daoUsuario.verificarMudancasSensiveis(usuarioNovo, idusuario)) {
			String token = TokenUtil.gerarTokenUnico();

			if (daoUsuarioPendente.verificarUsuarioPendenteExiste(usuarioNovo.getEmail())) {
				daoUsuarioPendente.atualizarDados(nome, email, senha, token);

			} else {
				daoUsuarioPendente.registrarUsuarioPendente(nome, email, senha, token);
			}
			daoUsuarioPendente.adicionarFotoUsuarioPendente(email, token, fotoPerfil);
			EmailAtualizarUtil.enviarEmailAtualizacao(email, nome, token, idusuario);
			response.sendRedirect("editarPerfil?statusEmail=enviado");
		} else {

			daoUsuario.atualizarUsuario(usuarioNovo, request.getServletPath());
			daoUsuario.atualizarFotoUsuario(usuarioNovo.getIdusuario(), fotoPerfil);
			request.getSession().setAttribute("usuarioLogado", usuarioNovo);
			response.sendRedirect("perfil?statusDados=atualizado");
		}

	}

	private void atualizarDados(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idusuario = request.getParameter("idusuario");
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		Usuario usuario = daoUsuarioPendente.verificarToken(email, token);

		if (usuario != null) {
			usuario.setIdusuario(idusuario);

			daoUsuario.atualizarUsuario(usuario, request.getServletPath());
			daoUsuarioPendente.apagarUsuarioPendente(email, token);
			request.getSession().setAttribute("usuarioLogado", usuario);
			response.sendRedirect("perfil?statusDados=atualizado");
		} else {
			response.sendRedirect("index.jsp?ConfirmErro=tokenInvalido");
		}
	}

	public byte[] capturarFotoDePerfil(HttpServletRequest request) throws IOException, ServletException {
		Part filePart = request.getPart("fotoPerfil");
		byte[] fotoPerfil = null;

		// Se a foto foi alterada
		if (filePart != null && filePart.getSize() > 0) {
			try (InputStream inputStream = filePart.getInputStream()) {
				fotoPerfil = inputStream.readAllBytes();
			}
		} else {
			// Se a foto não foi alterada, usamos a foto padrão
			try (InputStream inputStream = getServletContext().getResourceAsStream("/imagens/user.png")) {
				fotoPerfil = inputStream.readAllBytes();
			}
		}

		return fotoPerfil;
	}

	private String verificarSeMudouSenha(String senhaDigitada, String idusuario) {
		String senhaAtual = daoUsuario.obterSenhaUsuario(idusuario);
		String senha;

		if (senhaDigitada == null || senhaDigitada.trim().isEmpty()) {
			senha = senhaAtual;
			
		} else {
			if (SenhaUtil.verificarSenha(senhaDigitada, senhaAtual)) {
				senha = senhaAtual;
				
			} else {
				senha = SenhaUtil.criarHashSenha(senhaDigitada);
			}
		}

		return senha;
	}

}
