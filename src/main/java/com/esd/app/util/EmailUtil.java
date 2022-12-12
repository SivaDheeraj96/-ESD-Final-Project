package com.esd.app.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

//	private static final String FROM_EMAIL= "obulamdheeraj2@gmail.com";
//	private static final String PASSWORD = "jjefibfokhkepzsh";
//	private static final String HOST= "smtp.gmail.com";
	
	public  boolean sendEmail(String email, String content, Object attachments ) {
		System.out.println("inside util");
	 String FROM_EMAIL= "obulamdheeraj2@gmail.com";
		final String PASSWORD = "jjefibfokhkepzsh";
		final String HOST= "smtp.gmail.com";
		final Properties prop = new Properties();
		
		{
			prop.put("mail.smtp.host", HOST);
	        prop.put("mail.smtp.port", "465");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        prop.put("mail.smtp.socketFactory.port", "465");
	        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		Session session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            });
		session.setDebug(true);
		System.out.println("after session");
		try {
			Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("obulamdheeraj2@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("obulam.dheeraj@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");
            System.out.println("sending");
            Transport.send(message);

            System.out.println("Done");
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
                
		return true;
		
	}
	
//	@Autowired
//	JavaMailSender mailSender;
//	
//	public  boolean sendEmail2(String email, String content, Object attachments ) {
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		  
//        try {
// 
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
// 
//            mimeMessageHelper.setSubject("Itinerary for your Flight");
//            mimeMessageHelper.setTo(email);
//            mimeMessageHelper.setText("E-Ticket for Your Flight Booking ID:");
////            mimeMessageHelper.addAttachment("Itinerary",new File(attachmentLoc));
//            mailSender.send(mimeMessageHelper.getMimeMessage());
//            System.out.println("Email Sent");
//        }
//        
//	 catch(MessagingException e) {
//    e.printStackTrace();
//	}
//		return true;
//		
//	}

}
