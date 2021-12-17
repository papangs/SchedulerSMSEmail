package com.pank.scheduler.scheduler.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SchedulerController {

	Logger logger = LoggerFactory.getLogger(SchedulerController.class);

	@Scheduled(cron = "* * * * * ?")
	public void cronEmail() {
		
		logger.info("[Scheaduler - ] Start Send Email");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);
		
		
	}
	
	@RequestMapping(value = "scheduler", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Scheduler");
		
		return body;
	}

}
