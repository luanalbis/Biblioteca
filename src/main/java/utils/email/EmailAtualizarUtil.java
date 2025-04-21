package utils.email;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailAtualizarUtil {

    public static void enviarEmailAtualizacao(String destinatario,String nomeUsuario, String token,String idusuario) throws MessagingException {
        // Configurações de segurança do JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Adiciona os protocolos suportados pelo Java 8+
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Definir a autenticação
        final String usuario = "library.verificar.email@gmail.com";
        final String senha = "kgsw ylhi kwqq sscy";

        // Criar a sessão
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {
            // Criar o e-mail
            String assunto = "Atualizar seus dados";
            String conteudo = "Clique no link abaixo para atualizar seus dados:\n"
            		+"http://localhost:8080/Biblioteca/atualizarDados?idusuario="+idusuario+"&email=" 
                    + URLEncoder.encode(destinatario, "UTF-8")
                    + "&token=" + URLEncoder.encode(token, "UTF-8");

            MimeMessage mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(usuario));
            mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensagem.setSubject(assunto);
            mensagem.setText(conteudo);

            // Enviar o e-mail
            Transport.send(mensagem);
            System.out.println("E-mail de confirmação enviado com sucesso.");

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail.");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
