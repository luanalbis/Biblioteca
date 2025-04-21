package utils.email;

import java.util.UUID;

public class TokenUtil {

	public static String gerarTokenUnico() {
		return UUID.randomUUID().toString();
	}
}
