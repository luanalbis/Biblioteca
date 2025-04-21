package model.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Livro;
import utils.DAOUtil;

public class DAOlivro {

	public Connection testeConexao() throws SQLException {

		try (Connection con = DAOUtil.conectar();) {

			return con;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ArrayList<Livro> mostrarLivros(String idusuario) {
		ArrayList<Livro> livros = new ArrayList<Livro>();
		String read = "Select * from cadastro where iduser = ? order by titulo";

		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read);) {
			pst.setString(1, idusuario);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idcadastro = rs.getString(1);
				String titulo = rs.getString(2);
				String autor = rs.getString(3);
				String genero = rs.getString(4);
				String estado = rs.getString(5);
				String descricao = rs.getString(6);
				livros.add(new Livro(idcadastro, titulo, autor, genero, estado, descricao, idusuario));
			}

			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return livros;
	}

	public void adicionarLivro(Livro livro) {
		String create = "insert into cadastro values(null,?,?,?,?,?,?)";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(create)) {
			pst.setString(1, livro.getTitulo());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getGenero());
			pst.setString(4, livro.getEstado());
			pst.setString(5, livro.getDescricao());
			pst.setString(6, livro.getIduser());

			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public Livro selecionarLivro(String idcadastro, String iduser) {
		Livro livro = null;
		String read = "select * from cadastro where idcadastro = ? and iduser =?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(read)) {

			pst.setString(1, idcadastro);
			pst.setString(2, iduser);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				livro = new Livro();
				livro.setIdcadastro(rs.getString(1));
				livro.setTitulo(rs.getString(2));
				livro.setAutor(rs.getString(3));
				livro.setGenero(rs.getString(4));
				livro.setEstado(rs.getString(5));
				livro.setDescricao(rs.getString(6));
				return livro;
			}

			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return livro;
	}

	public void deletarLivro(String idcadastro, String iduser) {
		String delete = "Delete from cadastro where idcadastro = ? and iduser = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(delete)) {
			pst.setString(1, idcadastro);
			pst.setString(2, iduser);
			pst.executeUpdate();
		} catch (Exception e) {
		}

	}

	public void atualizarLivro(Livro livro) {
		String update = "update cadastro set titulo = ?, autor = ?,genero = ? ,estado =?, descricao =? where idcadastro = ? and iduser = ?";
		try (Connection con = DAOUtil.conectar(); PreparedStatement pst = con.prepareStatement(update)) {
			pst.setString(1, livro.getTitulo());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getGenero());
			pst.setString(4, livro.getEstado());
			pst.setString(5, livro.getDescricao());
			pst.setString(6, livro.getIdcadastro());
			pst.setString(7, livro.getIduser());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
 
}
