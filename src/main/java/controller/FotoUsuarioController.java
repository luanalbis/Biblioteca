package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Usuario;

@WebServlet("/fotoUsuario")
public class FotoUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FotoUsuarioController() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtém o objeto usuarioLogado da sessão
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

		// Verifica se o usuário está logado e se ele tem uma foto de perfil
		byte[] fotoPerfil = null;
		if (usuarioLogado != null) {
			fotoPerfil = usuarioLogado.getFoto(); // Foto já salva no objeto usuarioLogado
		}

		// Se não houver foto, usa a foto padrão
		if (fotoPerfil == null) {
			fotoPerfil = obterFotoPadrao();
		}

		// Define o tipo de conteúdo da resposta (no caso, imagem JPEG)
		response.setContentType("image/jpeg");

		// Envia os bytes da imagem como resposta
		try (OutputStream out = response.getOutputStream()) {
			out.write(fotoPerfil);
		}
	}

	// Método que retorna a foto padrão (user.png) se não houver foto no objeto
	private byte[] obterFotoPadrao() {
		try (InputStream inputStream = getServletContext().getResourceAsStream("/imagens/user.png")) {
			return inputStream.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
