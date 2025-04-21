package utils.password;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaUtil {
	public static String criarHashSenha(String senhaPlana) {
		return BCrypt.hashpw(senhaPlana, BCrypt.gensalt());
	}

	// Verificar senha digitada com a hash do banco
	public static boolean verificarSenha(String senhaPlana, String hash) {
		return BCrypt.checkpw(senhaPlana, hash);
	}
}
