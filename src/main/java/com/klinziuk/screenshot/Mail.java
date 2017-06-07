package com.klinziuk.screenshot;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Send Screenshots
 *
 */
public class Mail {

	private final String to = "to";
	private final String from = "from";
	private final String username = "username";
	private final String password = "password";
	private Properties properties;
	private Session session;
	private Message message;
	private MimeMultipart multipart;
	String filename;
	DataSource source;

	public void sendMessage() {
		properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("ScreenShots");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hey Klindziuk, there is some screenshots for you " + new Date().toString());
			multipart = new MimeMultipart("mixed");
			multipart.addBodyPart(messageBodyPart);

			// Files attachment
			File file = new File("D:/Selenium/");
			System.out.println("Attaching files:");
			for (File directoryItem : file.listFiles()) {
				if (directoryItem.isFile()) {
					MimeBodyPart messageBodyPart2 = new MimeBodyPart();
					FileDataSource fileDataSource = new FileDataSource(directoryItem);
					messageBodyPart2.setDataHandler(new DataHandler(fileDataSource));
					messageBodyPart2.setFileName(directoryItem.getName());
					messageBodyPart2.setDisposition(MimeBodyPart.INLINE);
					multipart.addBodyPart(messageBodyPart2);
					System.out.println(directoryItem.getName());
				}
			}
			System.out.println("Files attached succesfully");
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Mail sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}