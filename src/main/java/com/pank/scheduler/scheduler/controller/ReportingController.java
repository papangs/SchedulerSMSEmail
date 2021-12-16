package com.pank.scheduler.scheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ReportingController {

	Logger logger = LoggerFactory.getLogger(ReportingController.class);
	
	@RequestMapping(value = "reporting", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Report");
		
		return body;
	}
	
}
