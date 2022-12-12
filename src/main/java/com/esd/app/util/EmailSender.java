package com.esd.app.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendingMail(String to, String subject, String body, String attachment) throws MessagingException {
		System.out.println("inside util");
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper=new MimeMessageHelper(message,true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body);
		helper.addAttachment("Ticket PDF",new File(attachment));
		javaMailSender.send(message);
		System.out.println("after sending");
	}
}
