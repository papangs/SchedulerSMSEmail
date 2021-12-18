package com.pank.scheduler.scheduler.coba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class tes {

	static final String DB_URL = "jdbc:mysql://localhost:3306/scheduler";
	static final String USER = "root";
	static final String PASS = "";

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(tes.class);
		
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {

			String query9 = "SELECT param FROM parameter WHERE group_param='" + "THREAD" + "' AND kode_param='"
					+ "format.date" + "'";
			Statement st9 = conn.createStatement();
			ResultSet rs9 = st9.executeQuery(query9);
			String param9= "";
			while (rs9.next()) {
				param9 = rs9.getString("param");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat(param9);
			Date now = new Date();
			String strDate = sdf.format(now);

			logger.info("[Scheaduler - ] Start Send Email " + strDate);

			String toEmail = "irawanpapangsubakti@gmail.com"; // can be any email id

			logger.info("[Scheaduler - ] Email Start");

			Properties props = new Properties();
//			props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
////			props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
////			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
//			props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
//			props.put("mail.smtp.port", "465"); // SMTP Port
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.user", "irawanpapangsubakti28@gmail.com");

			props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
			props.put("mail.smtp.port", "465"); // SMTP Port
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", "irawanpapangsubakti28@gmail.com");
	        
			Session session = Session.getDefaultInstance(props);

			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress("irawanpapangsubakti28@gmail.com"));
			InternetAddress[] toAddresses = { new InternetAddress("irawanpapangsubakti@gmail.com") };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("PANK");
			msg.setSentDate(new Date());
			msg.setText("sddsfdsfdsfdsff");

			Transport t = session.getTransport("smtp");
			t.connect("irawanpapangsubakti28@gmail.com", "28papang");
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			
//			Authenticator auth = new Authenticator() {
//				// override the getPasswordAuthentication method
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("irawanpapangsubakti28@gmail.com", "28papang");
//				}
//			};
//
//			Session session = Session.getDefaultInstance(props, auth);
//
//			logger.info("[Scheaduler - ] Session created");
//
//			methodEmail(session, toEmail, "afsadsad", "sdfafsgsedf");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void methodEmail(Session session, String toEmail, String subject, String body) {

		Logger logger = LoggerFactory.getLogger(tes.class);
		
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {

			String query = "SELECT param FROM parameter WHERE group_param='" + "EMAIL-GMAIL" + "' AND kode_param='"
					+ "from.name" + "'";
			String query1 = "SELECT param FROM parameter WHERE group_param='" + "EMAIL-GMAIL" + "' AND kode_param='"
					+ "to.reply" + "'";
			String query2 = "SELECT param FROM parameter WHERE group_param='" + "EMAIL-GMAIL" + "' AND kode_param='"
					+ "from.reply" + "'";
			
			Statement st = conn.createStatement();
			Statement st1 = conn.createStatement();
			Statement st2 = conn.createStatement();

			ResultSet rs = st.executeQuery(query);
			ResultSet rs1 = st1.executeQuery(query1);
			ResultSet rs2 = st2.executeQuery(query2);

			String param = "";
			String param1 = "";
			String param2 = "";
			
			while (rs.next()) {
				param = rs.getString("param");
			}
			while (rs1.next()) {
				param1 = rs1.getString("param");
			}
			while (rs2.next()) {
				param2 = rs2.getString("param");
			}
			
			String fromname = param;
			String toreply = param1;
			String fromreply = param2;

			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(fromreply, fromname));
			msg.setReplyTo(InternetAddress.parse(toreply, false));
			msg.setSubject(subject, "UTF-8");
			msg.setContent(body, "text/html");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			logger.info("[Scheaduler - ] Message is ready");

			Transport.send(msg);

			logger.info("[Scheaduler - ] Email Send Successfully!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
