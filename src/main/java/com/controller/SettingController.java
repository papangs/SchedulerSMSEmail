package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
public class SettingController {

	Logger logger = LoggerFactory.getLogger(SettingController.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Setting");
		
		return body;
	}
}
