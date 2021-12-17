package com.pank.scheduler.scheduler.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.pank.scheduler.scheduler.gmail.SendEmail;

@RestController
@RequestMapping("/setting")
public class SettingController {

	Logger logger = LoggerFactory.getLogger(SettingController.class);
	
	@RequestMapping(value = "/sms-twilio", method = RequestMethod.POST)
	public String settingTwilio(@RequestBody String body) {

		logger.info("[Setting - Twilio] Start Setting SMS Twilio");
		
		return body;
	}
	
	@RequestMapping(value = "/email-gmail", method = RequestMethod.POST)
	public String settingEmail(@RequestBody String body) {

		logger.info("[Setting - Email] Start Setting Email Gmail");
		
		SendEmail email = new SendEmail();
		
		String fromEmail = "irawanpapangsubakti28@gmail.com"; // requires valid gmail id
		String password = "28papang"; // correct password for gmail id
		String toEmail = "irawanpapangsubakti@gmail.com"; // can be any email id

		System.out.println("SSLEmail Start");
		
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
		
		System.out.println("Session created");
		
		email.methodEmail(session, toEmail, "Fix Digital Test 2 Irawan Papang Subakti",
				"This is actual message embedded in HTML tags");
		
		return body;
	}
	
	@RequestMapping(value = "/scheduled-cron", method = RequestMethod.POST)
	public String settingScheduledCron(@RequestBody String body) {

		logger.info("[Setting - Scheduled Cron ] Start Setting Scheduled Cron");
		
		return body;
	}
}
