package com.pank.scheduler.scheduler.controller;

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
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.pank.scheduler.scheduler.entity.master.Subscriber;
import com.pank.scheduler.scheduler.service.SettingMapper;
import com.pank.scheduler.scheduler.service.SubscriberMapper;

@RestController
@RequestMapping("/")
public class SchedulerController {

	Logger logger = LoggerFactory.getLogger(SchedulerController.class);

	@Autowired
	private SettingMapper settingMapper;

	@Autowired
	private SubscriberMapper subscriberMapper;

	public SchedulerController() {
		SendThread send = new SendThread();
		send.start();
	}

	private class SendThread extends Thread {
		public void run() {
			try {
				while (true) {

					cronEmail();
					Thread.sleep(1000);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

//	@Scheduled(fixedRate = 1000)
	public void cronEmail() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdf.format(now);

		logger.info("[Scheaduler - ] Start Send Email " + strDate);

		try {

			String fromEmail = settingMapper.getParam("EMAIL-GMAIL", "from.email");
			String password = settingMapper.getParam("EMAIL-GMAIL", "from.password");
			String expressionCron = settingMapper.getParam("SCHEDULED-CRON", "cron.expression");
			String subjectCron = settingMapper.getParam("SCHEDULED-CRON", "cron.subject");
			String messasgeCron = settingMapper.getParam("SCHEDULED-CRON", "cron.messasge");

			if (expressionCron.equals(strDate)) {

				List<Subscriber> subscriber = subscriberMapper.findSubcribers();

				for (Subscriber res : subscriber) {

					logger.info("[Scheaduler - ] Cek Date Cron = " + expressionCron);

					String toEmail = res.getEmail(); // can be any email id

					logger.info("[Scheaduler - ] Email Start");

					Properties props = new Properties();
					props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
					props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
					props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
					props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
					props.put("mail.smtp.port", "465"); // SMTP Port

					Authenticator auth = new Authenticator() {
						// override the getPasswordAuthentication method
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(fromEmail, password);
						}
					};

					Session session = Session.getDefaultInstance(props, auth);

					logger.info("[Scheaduler - ] Session created");

					methodEmail(session, toEmail, subjectCron, messasgeCron);
//					break;
				}

			} else {

				logger.info("[Scheaduler - ] Send Email Pada Waktu = " + expressionCron);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@RequestMapping(value = "scheduler", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Scheduler");

		return body;
	}

	// ========================================== FUNCTION
	// ========================================================

	private void methodEmail(Session session, String toEmail, String subject, String body) {

		try {

			String fromname = settingMapper.getParam("EMAIL-GMAIL", "from.name");
			String toreply = settingMapper.getParam("EMAIL-GMAIL", "to.reply");
			String fromreply = settingMapper.getParam("EMAIL-GMAIL", "from.reply");

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

			logger.info("[Scheaduler - ] Email Sent Successfully!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
