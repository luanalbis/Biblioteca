package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entity.Usuario;
import utils.DAOUtil;
import utils.password.SenhaUtil;

public class DAOusuarioPendente {

	public void registrarUsuarioPendente(String nome, String email, String senha, String token) {
		String create = "Insert into usuarioPendente values(null,?,?,?,?,null)";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(create)) {
			pst.setString(1, nome);
			pst.setString(2, email);
			pst.setString(3, senha);
			pst.setString(4, token);
			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void adicionarFotoUsuarioPendente(String email, String token, byte[] fotoPerfil) {
		String create = "UPDATE usuarioPendente SET foto_perfil = ? WHERE email = ? and token = ?;";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(create)) {
			pst.setBytes(1, fotoPerfil);
			pst.setString(2, email);
			pst.setString(3, token);
			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public boolean verificarUsuarioPendenteExiste(String login) {
		String read = "select * from usuarioPendente where email = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, login);
			ResultSet rs = pst.executeQuery();

			return rs.next();

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public void atualizarDados(String nome, String email, String senha, String token) {
		String update = "update usuarioPendente set nome = ? ,senha = ?, token=? where email = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(update)) {
			pst.setString(1, nome);
			pst.setString(2, senha);
			pst.setString(3, token);
			pst.setString(4, email);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public Usuario verificarToken(String email, String token) {
		Usuario usuario = new Usuario();
		String read = "select * from usuarioPendente where email = ? and token = ?";

		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, email);
			pst.setString(2, token);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				usuario.setIdusuario(null);
				usuario.setNome(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));
				usuario.setFoto(null);
				return usuario;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public void apagarUsuarioPendente(String email, String token) {

		String delete = "delete from usuarioPendente where email = ? and token = ?";

		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(delete)) {
			pst.setString(1, email);
			pst.setString(2, token);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
