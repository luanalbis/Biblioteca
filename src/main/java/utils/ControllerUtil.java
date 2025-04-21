package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Usuario;

public class ControllerUtil {
	
	// Verifica se o usuário está logado, senão redireciona ao index
	public static boolean verificarSessao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogado") == null) {
			response.sendRedirect("index.jsp");
			return false;
		}
		return true;
	}
	
	// Retorna os dados usuário logado (Objeto)
	public static Usuario obterUsuarioLogado(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (Usuario) session.getAttribute("usuarioLogado");
		}
		return null;
	}
	
	
}
