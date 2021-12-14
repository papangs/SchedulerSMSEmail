package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subcriber")
public class SubcriberController {

	Logger logger = LoggerFactory.getLogger(SubcriberController.class);
	
	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	public String addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Add Data");
		
		return body;
	}

	@RequestMapping(value = "/addDatas", method = RequestMethod.POST)
	public String addDatas(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Add Data");
		
		return body;
	}
	
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	public String getData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Get Data");
		
		return body;
	}
	
	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	public String deleteData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Delete Data");
		
		return body;
	}
	
	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
	public String updateData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Update Data");
		
		return body;
	}
}
