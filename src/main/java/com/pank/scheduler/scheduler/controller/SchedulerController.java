package com.pank.scheduler.scheduler.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pank.scheduler.scheduler.entity.master.Subscriber;
import com.pank.scheduler.scheduler.service.SubscriberMapper;
import com.twilio.Twilio;

import ch.qos.logback.core.joran.conditional.IfAction;

import com.pank.scheduler.scheduler.constant.ConstantValidasi;

@RestController
@RequestMapping("/")
public class SchedulerController {

	Logger logger = LoggerFactory.getLogger(SchedulerController.class);

	static final String DB_URL = "jdbc:mysql://localhost:3306/scheduler";
	static final String USER = "root";
	static final String PASS = "";

	@Autowired
	private SubscriberMapper subscriberMapper;

	private String sukses = "";
	
	public SchedulerController() {
		SendThread send = new SendThread();
		send.start();
	}
	
	private class SendThread extends Thread {
		public void run() {
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				
				while (true) {

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

					String query = "SELECT param FROM parameter WHERE group_param='" + "EMAIL-GMAIL" + "' AND kode_param='"
							+ "from.email" + "'";
					String query1 = "SELECT param FROM parameter WHERE group_param='" + "EMAIL-GMAIL" + "' AND kode_param='"
							+ "from.password" + "'";
					String query2 = "SELECT param FROM parameter WHERE group_param='" + "SCHEDULED-CRON" + "' AND kode_param='"
							+ "cron.expression" + "'";
					String query3 = "SELECT param FROM parameter WHERE group_param='" + "EMAIL-GMAIL" + "' AND kode_param='"
							+ "subject" + "'";
					String query4 = "SELECT param FROM parameter WHERE group_param='" + "SCHEDULED-CRON" + "' AND kode_param='"
							+ "cron.messasge" + "'";
					String query5 = "SELECT param FROM parameter WHERE group_param='" + "SMS-TWILIO" + "' AND kode_param='"
							+ "sid" + "'";
					String query6 = "SELECT param FROM parameter WHERE group_param='" + "SMS-TWILIO" + "' AND kode_param='"
							+ "token" + "'";
					String query7 = "SELECT param FROM parameter WHERE group_param='" + "SMS-TWILIO" + "' AND kode_param='"
							+ "number.from" + "'";
					String query8 = "SELECT param FROM parameter WHERE group_param='" + "THREAD" + "' AND kode_param='"
							+ "setting.thread" + "'";
					
					Statement st = conn.createStatement();
					Statement st1 = conn.createStatement();
					Statement st2 = conn.createStatement();
					Statement st3 = conn.createStatement();
					Statement st4 = conn.createStatement();
					Statement st5 = conn.createStatement();
					Statement st6 = conn.createStatement();
					Statement st7 = conn.createStatement();
					Statement st8 = conn.createStatement();
					
					ResultSet rs = st.executeQuery(query);
					ResultSet rs1 = st1.executeQuery(query1);
					ResultSet rs2 = st2.executeQuery(query2);
					ResultSet rs3 = st3.executeQuery(query3);
					ResultSet rs4 = st4.executeQuery(query4);
					ResultSet rs5 = st5.executeQuery(query5);
					ResultSet rs6 = st6.executeQuery(query6);
					ResultSet rs7 = st7.executeQuery(query7);
					ResultSet rs8= st8.executeQuery(query8);

					String param = "";
					String param1 = "";
					String param2 = "";
					String param3 = "";
					String param4 = "";
					String param5 = "";
					String param6 = "";
					String param7 = "";
					long param8 = 0;
					
					while (rs.next()) {
						param = rs.getString("param");
					}
					while (rs1.next()) {
						param1 = rs1.getString("param");
					}
					while (rs2.next()) {
						param2 = rs2.getString("param");
					}
					while (rs3.next()) {
						param3 = rs3.getString("param");
					}
					while (rs4.next()) {
						param4 = rs4.getString("param");
					}
					while (rs5.next()) {
						param5 = rs5.getString("param");
					}
					while (rs6.next()) {
						param6 = rs6.getString("param");
					}
					while (rs7.next()) {
						param7 = rs7.getString("param");
					}
					while (rs8.next()) {
						param8 = rs8.getLong("param");
					}
					
					String fromEmail = param;
					String password = param1;
					String expressionCron = param2;
					String subjectCron = param3;
					String messasgeCron = param4;
					String ACCOUNT_SID = param5;
					String AUTH_TOKEN = param6;
					String fromNumber = param7;

					if (expressionCron.equals(strDate)) {

						List<Subscriber> subscriber = subscriberMapper.findSubcribers();

						for (Subscriber res : subscriber) {

							logger.info("[Scheaduler - ] Cek Date Cron = " + expressionCron);

							if(res.getTipe().equals(ConstantValidasi.ValSMS)) {
								
								Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
								com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
						                new com.twilio.type.PhoneNumber(res.getNohp()),
						                new com.twilio.type.PhoneNumber(fromNumber),
						                "Hi "+res.getNama()+"\n\n"+messasgeCron+"\n\n"+"Regards,\nApp").create();

								logger.info("[Scheaduler - ] SMS Send Successfully!! "+message.getSid());

								String status = "";
								
								if(message.getSid().isEmpty()) {
									status = "GAGAL";
								}else {
									status = "SUKSES";
								}
								
								String sql = "insert into history_subcriber set \n"
			                            + "id_subcriber='" + res.getId() + "',"
			                            + "tanggal_kirim='" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "',"
			                            + "jam_kirim='" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "',"
			                            + "status='" + status + "'";

			                    PreparedStatement p22 = conn.prepareStatement(sql);
			                    p22.executeUpdate();
			                    p22.close();
			                    
							}else {
								
								String toEmail = res.getEmail(); // can be any email id

								logger.info("[Scheaduler - ] Email Start");

								Properties props = new Properties();
								props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
								props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
								props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
								props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
								props.put("mail.smtp.port", "465"); // SMTP Port
//								props.put("mail.smtp.starttls.enable", "true");

								Authenticator auth = new Authenticator() {
									// override the getPasswordAuthentication method
									protected PasswordAuthentication getPasswordAuthentication() {
										return new PasswordAuthentication(fromEmail, password);
									}
								};

								Session session = Session.getDefaultInstance(props, auth);

								logger.info("[Scheaduler - ] Session created");

								methodEmail(session, toEmail, subjectCron, "Hi "+res.getNama()+" "+messasgeCron+" "+"Regards, App");

								String status = "";
								
								if(sukses.equals("")) {
									status = "GAGAL";
								}else {
									status = "SUKSES";
								}
								
								String sql = "insert into history_subcriber set \n"
			                            + "id_subcriber='" + res.getId() + "',"
			                            + "tanggal_kirim='" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "',"
			                            + "jam_kirim='" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "',"
			                            + "status='" + status + "'";

			                    PreparedStatement p22 = conn.prepareStatement(sql);
			                    p22.executeUpdate();
			                    p22.close();
							}

						}

					} else {

						logger.info("[Scheaduler - ] Send SMS and Email Pada Waktu = " + expressionCron);

					}
					
					Thread.sleep(param8);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// ========================================== FUNCTION ========================================================

	private void methodEmail(Session session, String toEmail, String subject, String body) {

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

			sukses = "Email Send Successfully!!";
			
			logger.info("[Scheaduler - ] "+sukses);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
