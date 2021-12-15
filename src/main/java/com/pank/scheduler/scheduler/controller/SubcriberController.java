package com.pank.scheduler.scheduler.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pank.scheduler.scheduler.constant.ConstantCode;
import com.pank.scheduler.scheduler.entity.master.Subscriber;
import com.pank.scheduler.scheduler.entity.subcriber.RequestSubscriber;
import com.pank.scheduler.scheduler.entity.subcriber.ResponseJsonSubscriber;
import com.pank.scheduler.scheduler.entity.subcriber.ResponseObjectSubscriber;
import com.pank.scheduler.scheduler.entity.subcriber.ResponseSubscriber;
import com.pank.scheduler.scheduler.service.SubscriberMapper;

//@Component
@RestController
@RequestMapping("/subcriber")
public class SubcriberController {

	Logger logger = LoggerFactory.getLogger(SubcriberController.class);

	@Autowired
	private SubscriberMapper subscriberMapper;

	private String tXid;

	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	public ResponseSubscriber addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Add Data");

		RequestSubscriber requestSubscriber = new RequestSubscriber();

		ResponseSubscriber responseSubscriber = new ResponseSubscriber();
		ResponseObjectSubscriber responseObjectSubscriber = new ResponseObjectSubscriber();
		ResponseJsonSubscriber responseJsonSubscriber = new ResponseJsonSubscriber();

		ObjectMapper mapper = new ObjectMapper();

		try {

			requestSubscriber = mapper.readValue(body, RequestSubscriber.class);

			if (requestSubscriber.getNama().equals("") || requestSubscriber.getNama().isEmpty()
					|| requestSubscriber.getNama().equals(null)) {

				logger.info("[Scheaduler - ] Error Nama");
				responseObjectSubscriber.setRescode(ConstantCode.ErrCode01);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode01Desc);

			} else if (requestSubscriber.getEmail().equals("") || requestSubscriber.getEmail().isEmpty()
					|| requestSubscriber.getEmail().equals(null)) {

				logger.info("[Scheaduler - ] Error Email");
				responseObjectSubscriber.setRescode(ConstantCode.ErrCode02);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode02Desc);

			} else if (requestSubscriber.getNohp().equals("") || requestSubscriber.getNohp().isEmpty()
					|| requestSubscriber.getNohp().equals(null)) {

				logger.info("[Scheaduler - ] Error No Hp");
				responseObjectSubscriber.setRescode(ConstantCode.ErrCode03);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode03Desc);

			} else if (requestSubscriber.getTipe().equals("") || requestSubscriber.getTipe().isEmpty()
					|| requestSubscriber.getTipe().equals(null)) {

				logger.info("[Scheaduler - ] Error Type");
				responseObjectSubscriber.setRescode(ConstantCode.ErrCode04);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode04Desc);

			} else if (!requestSubscriber.getTipe().equals("SMS")) {

				logger.info("[Scheaduler - ] Error Type to SMS");
				responseObjectSubscriber.setRescode(ConstantCode.ErrCode05);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode05Desc);

			} else if (!requestSubscriber.getTipe().equals("EMAIL")) {

				logger.info("[Scheaduler - ] Error Type to Email");
				responseObjectSubscriber.setRescode(ConstantCode.ErrCode05);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode05Desc);

			} else {

				Integer iTrans = insertTrans(requestSubscriber);

				if (iTrans.equals(222)) {

					Subscriber cTrans = subscriberMapper.findSubcriberById(tXid);

					if (cTrans == null) {

						logger.info("[Scheaduler - ] Error Data is null");
						responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
						responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99Desc);

					} else {

						logger.info("[Scheaduler - ] Success");
						responseObjectSubscriber.setRescode(ConstantCode.ErrCode00);
						responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode00Desc);

						responseJsonSubscriber.setId(tXid);
						responseJsonSubscriber.setNama(cTrans.getNama());
						responseJsonSubscriber.setEmail(cTrans.getEmail());
						responseJsonSubscriber.setNohp(cTrans.getNohp());
						responseJsonSubscriber.setTipe(cTrans.getTipe());

						responseSubscriber.setObject(responseObjectSubscriber);
						responseSubscriber.setJsonData(responseJsonSubscriber);

						return responseSubscriber;
					}

				} else {

					logger.info("[Scheaduler - ] Error when input table subcriber.");
					responseObjectSubscriber.setRescode(ConstantCode.ErrCode63);
					responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode63Desc);

				}

			}

			responseSubscriber.setObject(responseObjectSubscriber);
			responseSubscriber.setJsonData(responseJsonSubscriber);

			return responseSubscriber;

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("[Scheaduler - ] Other Error");
			responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
			responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99);
			responseSubscriber.setObject(responseObjectSubscriber);
			responseSubscriber.setJsonData(responseJsonSubscriber);

			return responseSubscriber;
		}

	}

	@RequestMapping(value = "/getData")
	public ResponseSubscriber getData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Get Data");

		RequestSubscriber requestSubscriber = new RequestSubscriber();

		ResponseSubscriber responseSubscriber = new ResponseSubscriber();
		ResponseObjectSubscriber responseObjectSubscriber = new ResponseObjectSubscriber();
		ResponseJsonSubscriber responseJsonSubscriber = new ResponseJsonSubscriber();

		ObjectMapper mapper = new ObjectMapper();

		try {
			requestSubscriber = mapper.readValue(body, RequestSubscriber.class);

			Subscriber cTrans = subscriberMapper.findSubcriberById(requestSubscriber.getId());
			
			logger.info("[Scheaduler - ] Success");
			responseObjectSubscriber.setRescode(ConstantCode.ErrCode00);
			responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode00Desc);

			responseJsonSubscriber.setId(cTrans.getId());
			responseJsonSubscriber.setNama(cTrans.getNama());
			responseJsonSubscriber.setEmail(cTrans.getEmail());
			responseJsonSubscriber.setNohp(cTrans.getNohp());
			responseJsonSubscriber.setTipe(cTrans.getTipe());

			responseSubscriber.setObject(responseObjectSubscriber);
			responseSubscriber.setJsonData(responseJsonSubscriber);

			return responseSubscriber;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return responseSubscriber;
		}

	}

//	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
//	public String deleteData(@RequestBody String body) {
//
//		logger.info("[Scheaduler - ] Start Delete Data");
//		
//		return body;
//	}
//	
//	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
//	public String updateData(@RequestBody String body) {
//
//		logger.info("[Scheaduler - ] Start Update Data");
//		
//		return body;
//	}

	// ============================================== FUNCTION DATABASE
	// =============================================

	public Integer insertTrans(RequestSubscriber requestSubscriber) {

		try {

			HashMap<String, Object> inp = new HashMap<String, Object>();
			HashMap<String, Object> inp1 = new HashMap<String, Object>();

			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			Random randomInt = new Random();
			Integer intRand = randomInt.nextInt(999999);
			String strRand = String.format("%6s", intRand).replace(' ', '0');

			String ID = "SUB" + dateFormat.format(date) + strRand;

			inp.put("id", ID);
			inp.put("nama", requestSubscriber.getNama());
			inp.put("email", requestSubscriber.getEmail());
			inp.put("nohp", requestSubscriber.getNohp());
			inp.put("tipe", requestSubscriber.getTipe());

			logger.info("[Scheaduler - ] insert to subcriber : " + inp);

			Subscriber cTrans = subscriberMapper.findSubcriber(inp);

			if (cTrans == null) {

				inp1.put("id", ID);
				inp1.put("nama", requestSubscriber.getNama());
				inp1.put("email", requestSubscriber.getEmail());
				inp1.put("nohp", requestSubscriber.getNohp());
				inp1.put("tipe", requestSubscriber.getTipe());

				Integer sel1 = subscriberMapper.insertSubcriber(inp1);
				tXid = inp1.get("id").toString();

				if (sel1.equals(1) == false) {

					logger.info("[Scheaduler - ] insert to subcriber fail");
					return 111;
				}
			}

			logger.info("[Scheaduler - ] insert to subcriber success.");
			return 222;

		} catch (Exception e) {

			logger.info("[Scheaduler - ] insert to subcriber fail");
			e.printStackTrace();
			return 111;

		}
	}
}
