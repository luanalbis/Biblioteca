package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entity.Usuario;
import utils.DAOUtil;
import utils.password.SenhaUtil;

public class DAOusuario {

	public boolean verificarLoginExiste(String login) {
		String read = "select * from usuario where email = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, login);
			ResultSet rs = pst.executeQuery();

			return rs.next();

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean verificarSenhaCorreta(String login, String senha) {
		String read = "select senha from usuario where email = ?";
		String hash = "";

		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, login);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				hash = rs.getString(1);
			}

			return SenhaUtil.verificarSenha(senha, hash);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public String idDoUsuario(String login) {
		String read = "select idusuario from usuario where email = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, login);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public Usuario dadosUsuario(String login) {

		String read = "select * from usuario where email=?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, login);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String idusuario = rs.getString(1);
				String nome = rs.getString(2);
				String email = rs.getString(3);
				// String senha = rs.getString(4);
				byte[] foto = rs.getBytes(5);
				Usuario usuario = new Usuario(idusuario, nome, email);
				usuario.setFoto(foto);

				return usuario;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean adicionarNovoUsuario(Usuario usuario) {
		String create = "insert into usuario values(null,?,?,?,null)";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(create)) {
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

		return true;
	}

	public void atualizarUsuario(Usuario usuario, String action) {

		try (Connection con = DAOUtil.conectar();) {
			String update;
			PreparedStatement pst;
			if (action.equals("/atualizarDados") || action.equals("/changepassword")) {
				update = "update usuario set nome = ? ,email = ?, senha = ? where idusuario  = ?";
				pst = con.prepareStatement(update);
				pst.setString(1, usuario.getNome());
				pst.setString(2, usuario.getEmail());
				pst.setString(3, usuario.getSenha());
				pst.setString(4, usuario.getIdusuario());

			} else {
				update = "update usuario set nome = ? where idusuario  = ?";
				pst = con.prepareStatement(update);
				pst.setString(1, usuario.getNome());
				pst.setString(2, usuario.getIdusuario());

			}

			pst.executeUpdate();
			pst.close();

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public boolean verificarMudancasSensiveis(Usuario usuarioNovo, String idusuario) {
		String read = "select * from usuario where idusuario = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, idusuario);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String hash = rs.getString("senha");

				if (!rs.getString("email").equals(usuarioNovo.getEmail()) || !usuarioNovo.getSenha().equals(hash)) {
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			return true;
		}
		return false;
	}

	public void atualizarFotoUsuario(String idusuario, byte[] fotoPerfil) {
		String update = "update usuario set foto_perfil = ? where idusuario  = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(update)) {
			pst.setBytes(1, fotoPerfil);
			pst.setString(2, idusuario);

			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public String obterSenhaUsuario(String idusuario) {
		String hash = "";
		String read = "select senha from usuario where idusuario = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {
			pst.setString(1, idusuario);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				hash = rs.getString("senha");

			}

		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
		return hash;
	}

}
