package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.entity.subcriber.RequestSubscriber;
import com.entity.subcriber.ResponseJsonSubscriber;
import com.entity.subcriber.ResponseObjectSubscriber;
import com.entity.subcriber.ResponseSubscriber;

@RestController
@RequestMapping("/subcriber")
public class SubcriberController {

	Logger logger = LoggerFactory.getLogger(SubcriberController.class);
	
	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	public ResponseSubscriber addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Add Data");
		
		RequestSubscriber requestSubscriber = new RequestSubscriber();
		
		ResponseSubscriber responseSubscriber = new ResponseSubscriber();
		ResponseObjectSubscriber responseObjectSubscriber = new ResponseObjectSubscriber();
		ResponseJsonSubscriber responseJsonSubscriber = new ResponseJsonSubscriber();
		List<ResponseJsonSubscriber> responseJsonSubscribers = new ArrayList<ResponseJsonSubscriber>();
		
		try {
			
			
			
			return responseSubscriber;
			
		} catch (Exception e) {
			// TODO: handle exception
			responseObjectSubscriber.setRescode("99");
			responseObjectSubscriber.setRescodedesc("Other Error");
			responseSubscriber.setObject(responseObjectSubscriber);
			responseSubscriber.setJson(json);

			return responseSubscriber;
		}
		
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
