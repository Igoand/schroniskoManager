package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ObslugaMail {

	private String host;
	private int port;
	private String username;
	private String pass;
	private String[] listaZwierzat;

	// host smtp.wp.pl
	// port 587
	// login ad.grzyb03
	// pass 
	public ObslugaMail(String host, int port, String username, String pass, String[] listaZwierzat) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.pass = pass;
		this.listaZwierzat = listaZwierzat;

		wyslijMail();
	}

	private void wyslijMail() {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.ssl.trust", host);

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, pass);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sample_adress_mail@mail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sample_adress_mail@mail.com"));
			message.setSubject("Schronisko Info");

			String zwierzaki = "<br>";
			for (String zwierzak : listaZwierzat) {
				zwierzaki += zwierzak + "<br>";
			}

			String msg = "Ilość miejsc w schronisku osiągnęła 5 miesc!\nZwierzaki w schronisku to:" + zwierzaki;

			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(msg, "text/html; charset=UTF-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Wysłano maila");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
