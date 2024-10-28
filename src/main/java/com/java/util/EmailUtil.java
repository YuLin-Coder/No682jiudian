package com.java.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	// 设置服务器
	private static String KEY_SMTP = "mail.host";
	private static String VALUE_SMTP = "smtp.qq.com";

	private static String SMTP_PORT_KEY = "mail.smtp.port";
	private static String SMTP_PORT_VALUE = "465";
	// 服务器验证
	private static String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static String VALUE_AUTH = "true";
	// 发件人用户名、密码
	private String SEND_USER = "2164064701@qq.com";
	private String SEND_UNAME = "2164064701";
	private String SEND_PWD = "pybjrwvyencaeagj";//授权码
	// 建立会话
	private MimeMessage message;
	private Session s;

	/*
	 * 初始化方法
	 */
	public EmailUtil() {
		Properties props = System.getProperties();
		props.setProperty(KEY_SMTP, VALUE_SMTP);
		props.setProperty(MAIL_SMTP_AUTH, VALUE_AUTH);
		props.setProperty(SMTP_PORT_KEY, SMTP_PORT_VALUE);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", SMTP_PORT_VALUE);
		props.setProperty("mail.debug", "true");
		s = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
			}
		});
		s.setDebug(true);
		message = new MimeMessage(s);
	}

	/**
	 * 发送邮件
	 * 
	 * @param headName    邮件头文件名
	 * @param sendHtml    邮件内容
	 * @param receiveUser 收件人地址
	 */
	public void doSendHtmlEmail(String headName, String sendHtml, String receiveUser) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(SEND_USER);
			message.setFrom(from);
			// 收件人
			InternetAddress to = new InternetAddress(receiveUser);
			message.setRecipient(Message.RecipientType.TO, to);
			// 邮件标题
			message.setSubject(headName);
			String content = sendHtml.toString();
			// 邮件内容,也可以使纯文本"text/plain"
			message.setContent(content, "text/html;charset=GBK");
			message.saveChanges();
			Transport transport = s.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("send success!");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		EmailUtil se = new EmailUtil();
		se.doSendHtmlEmail("主题xxx", "内容xxx", "2164064701@qq.com");
	}
}
