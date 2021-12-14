package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reporting")
public class ReportingController {

	Logger logger = LoggerFactory.getLogger(ReportingController.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Report");
		
		return body;
	}
	
}
