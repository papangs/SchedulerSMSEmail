package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Login");
		
		return body;
	}
	
}
