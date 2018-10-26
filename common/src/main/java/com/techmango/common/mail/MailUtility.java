package com.techmango.common.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class MailUtility {

	// Get session for mail object
	public static Session getSession(String username, String password, String host) {

		String port = "";
		if(host.contains("gmail")) {port = "465";}
		if(host.contains("outlook")) {port = "587";}
		if(host.contains("yahoo")) {port = "465";}
		if(host.contains("zoho")) {port = "587";}
		/*if(host.contains("amazonaws")) {port = "465";}*/

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtps");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.setProperty("mail.tls", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);
		props.put("mail.debug", "true");  
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");


		return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

	}

	// Send Simple Text/Html Mail
	public static void sendSimpleMail(String from, String pwd, String[] to, String host, String subject,
			String mailbody, String[] cc, String[] bcc, boolean isHtmlContent, String filePath) {
		Session session = getSession(from, pwd, host);
		try {

			Message message = new MimeMessage(session);
			MimeMessageHelper helper = new MimeMessageHelper((MimeMessage) message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
					);
			String messageContent = ""; 
			
			// if the type of mail html then refer the freemarker template
			if(isHtmlContent) {
				// Free marker Template
				Configuration cfg = new Configuration();
				//			Assume that the template is available under /src/main/resources/templates
				cfg.setClassForTemplateLoading(MailUtility.class, "/templates/");
				cfg.setDefaultEncoding("UTF-8");
				cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
				Template template = cfg.getTemplate("emailTemplate.ftl");
	
				//Pass custom param values
				Map<String,String> paramMap = new HashMap<>();
				paramMap.put("name", to[0]);
				paramMap.put("ticketNumber", "TVST1235453");
				messageContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, paramMap);
			}
			// else will be sending the text mail
			else {
				messageContent = "Dear "+from+",\r\nThanks for contacting us. Your ticket number is TVST2423FD Our team is working on your request.\r\n" + 
						"We will update you shortly.We are here to help you.Stay Connected!!!\r\n" + "\r\n Regards,\r\n TVS";
			}

			helper.setTo(to);
			if(bcc != null && bcc.length > 0) {
				helper.setBcc(bcc);
			}
			if(cc != null && cc.length > 0) {
				helper.setCc(cc);
			}
			helper.setText(messageContent, isHtmlContent);
			if(isHtmlContent) {
				helper.addInline("logo.png", new ClassPathResource("tvs.png"));
			}
			helper.setFrom(from);
			helper.setSubject(subject);
			if(filePath != null && filePath.length() > 0) {
				FileSystemResource file = new FileSystemResource(filePath);
				helper.addAttachment(file.getFilename(), file);
			}
			Transport transport = session.getTransport();
			transport.connect();
			Transport.send(message);
			transport.close();
			System.out.println("Sent message successfully.... from GMAIL");		 	

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Send Mail With attachment
}
