package com.techmango.common.mail;

public class MailSender {

	// Send simple text mail
	public static void sendTextMail(String from, String pwd, String[] to, String host, String subject, String mailbody,
			String[] cc, String[] bcc) {
		MailUtility.sendSimpleMail(from, pwd, to, host, subject, mailbody, cc, bcc, false, ""); 
	}

	// Send simple HTML mail
	public static void sendHtmlMail(String from, String pwd, String[] to, String host, String subject, String mailbody,
			String[] cc, String[] bcc) {
		MailUtility.sendSimpleMail(from, pwd, to, host, subject, mailbody, cc, bcc, true, "");
	}

	// Send simple text mail with Attachment
	public static void sendTextMailWithAttachment(String from, String pwd, String[] to, String host, String subject,
			String mailbody, String[] cc, String[] bcc, String filePath) {
		MailUtility.sendSimpleMail(from, pwd, to, host, subject, mailbody, cc, bcc, false, filePath);
	}

	// Send simple HTML mail with Attachment
	public static void sendHtmlMailWithAttachment(String from, String pwd, String[] to, String host, String subject,
			String mailbody, String[] cc, String[] bcc, String filePath) {
		MailUtility.sendSimpleMail(from, pwd, to, host, subject, mailbody, cc, bcc, true, filePath);
	}
}
