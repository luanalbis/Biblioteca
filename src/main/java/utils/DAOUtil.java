package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOUtil {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/biblioteca?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "P@ssw0rd";

	public static Connection conectar() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println("Erro ao conectar: " + e.getMessage());
			return null;
		}
	}
}


