package com.pank.scheduler.scheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SettingController {

	Logger logger = LoggerFactory.getLogger(SettingController.class);
	
	@RequestMapping(value = "setting", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Setting");
		
		return body;
	}
}
