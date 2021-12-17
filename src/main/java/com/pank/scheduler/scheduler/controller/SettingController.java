package com.pank.scheduler.scheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pank.scheduler.scheduler.entity.setting.RequestSetting;
import com.pank.scheduler.scheduler.entity.setting.ResponseJsonSetting;
import com.pank.scheduler.scheduler.entity.setting.ResponseObjectSetting;
import com.pank.scheduler.scheduler.entity.setting.ResponseSetting;
import com.pank.scheduler.scheduler.service.SettingMapper;

@RestController
@RequestMapping("/setting")
public class SettingController {

	Logger logger = LoggerFactory.getLogger(SettingController.class);

	@Autowired
	private SettingMapper settingMapper;
	
	@RequestMapping(value = "/sms-twilio", method = RequestMethod.POST)
	public String settingTwilio(@RequestBody String body) {

		logger.info("[Setting - Twilio] Start Setting SMS Twilio");
		
		return body;
	}
	
	@RequestMapping(value = "/email-gmail", method = RequestMethod.POST)
	public ResponseSetting settingEmail(@RequestBody String body) {

		logger.info("[Setting - Email] Start Setting Email Gmail");
		
		RequestSetting requestSetting = new RequestSetting();

		ResponseSetting responseSetting = new ResponseSetting();
		ResponseObjectSetting responseObjectSetting = new ResponseObjectSetting();
		ResponseJsonSetting responseJsonSetting = new ResponseJsonSetting();
		
		try {
			
			
			
			return responseSetting;
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return responseSetting;
		}
	}
	
	@RequestMapping(value = "/scheduled-cron", method = RequestMethod.POST)
	public String settingScheduledCron(@RequestBody String body) {

		logger.info("[Setting - Scheduled Cron ] Start Setting Scheduled Cron");
		
		return body;
	}
}
