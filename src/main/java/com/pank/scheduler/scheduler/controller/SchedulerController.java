package com.pank.scheduler.scheduler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SchedulerController {

	Logger logger = LoggerFactory.getLogger(SchedulerController.class);
	
	@RequestMapping(value = "scheduler", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Scheduler");
		
		return body;
	}
}
