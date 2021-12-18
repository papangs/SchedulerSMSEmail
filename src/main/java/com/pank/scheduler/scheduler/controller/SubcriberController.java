package com.pank.scheduler.scheduler.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pank.scheduler.scheduler.constant.ConstantCode;
import com.pank.scheduler.scheduler.constant.ConstantValidasi;
import com.pank.scheduler.scheduler.entity.master.Subscriber;
import com.pank.scheduler.scheduler.entity.subcriber.RequestSubscriber;
import com.pank.scheduler.scheduler.entity.subcriber.ResponseJsonSubscriber;
import com.pank.scheduler.scheduler.entity.subcriber.ResponseObjectSubscriber;
import com.pank.scheduler.scheduler.entity.subcriber.ResponseObjectSubscriberDelete;
import com.pank.scheduler.scheduler.entity.subcriber.ResponseSubscriber;
import com.pank.scheduler.scheduler.service.SubscriberMapper;

@Component
@RestController
@RequestMapping("/subcriber")
public class SubcriberController {

	Logger logger = LoggerFactory.getLogger(SubcriberController.class);

	@Autowired
	private SubscriberMapper subscriberMapper;

	private String tXid;

	private String responseCode = null;
	private String responseDesc = null;

	public boolean validateDelete(RequestSubscriber requestSubscriber) {

		try {

			if (requestSubscriber.getNama().equals("") || requestSubscriber.getNama().isEmpty()
					|| requestSubscriber.getNama().equals(null)) {

				logger.info("[Subcriber - Validate-Delete] Error Nama");
				responseCode = ConstantCode.ErrCode01;
				responseDesc = ConstantCode.ErrCode01Desc;
				return false;

			} else if (requestSubscriber.getId().equals("") || requestSubscriber.getId().isEmpty()
					|| requestSubscriber.getId().equals(null)) {

				logger.info("[Scheaduler - Validate-Delete] Error ID Wrong");
				responseCode = ConstantCode.ErrCode56;
				responseDesc = ConstantCode.ErrCode56Desc;
				return false;

			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean validate(RequestSubscriber requestSubscriber) {

		try {

			if (requestSubscriber.getNama().equals("") || requestSubscriber.getNama().isEmpty()
					|| requestSubscriber.getNama().equals(null)) {

				logger.info("[Subcriber - Validate] Error Nama");
				responseCode = ConstantCode.ErrCode01;
				responseDesc = ConstantCode.ErrCode01Desc;
				return false;

			} else if (requestSubscriber.getEmail().equals("") || requestSubscriber.getEmail().isEmpty()
					|| requestSubscriber.getEmail().equals(null)) {

				logger.info("[Subcriber - Validate] Error Email");
				responseCode = ConstantCode.ErrCode02;
				responseDesc = ConstantCode.ErrCode02Desc;
				return false;

			} else if (requestSubscriber.getNohp().equals("") || requestSubscriber.getNohp().isEmpty()
					|| requestSubscriber.getNohp().equals(null)) {

				logger.info("[Subcriber - Validate] Error No Hp");
				responseCode = ConstantCode.ErrCode03;
				responseDesc = ConstantCode.ErrCode03Desc;
				return false;

			} else if (requestSubscriber.getTipe().equals("") || requestSubscriber.getTipe().isEmpty()
					|| requestSubscriber.getTipe().equals(null)) {

				logger.info("[Subcriber - Validate] Error Type");
				responseCode = ConstantCode.ErrCode04;
				responseDesc = ConstantCode.ErrCode04Desc;
				return false;

			} else if ((requestSubscriber.getTipe().equals(ConstantValidasi.ValSMS)) == false
					&& (requestSubscriber.getTipe().equals(ConstantValidasi.ValEMAIL)) == false) {

				logger.info("[Subcriber - Validate] Error Type to SMS and EMAIL");
				responseCode = ConstantCode.ErrCode05;
				responseDesc = ConstantCode.ErrCode05Desc;
				return false;

			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	public ResponseSubscriber addData(@RequestBody String body) {

		logger.info("[Subcriber - Add Data] Start Add Data");
		logger.info("The time is now {}", new SimpleDateFormat("HH:mm:ss").format(new Date()));
		
		RequestSubscriber requestSubscriber = new RequestSubscriber();

		ResponseSubscriber responseSubscriber = new ResponseSubscriber();
		ResponseObjectSubscriber responseObjectSubscriber = new ResponseObjectSubscriber();
		ResponseJsonSubscriber responseJsonSubscriber = new ResponseJsonSubscriber();

		ObjectMapper mapper = new ObjectMapper();

		try {

			requestSubscriber = mapper.readValue(body, RequestSubscriber.class);

			Boolean validate = validate(requestSubscriber);

			if (validate) {

				Integer iTrans = insert(requestSubscriber);

				if (iTrans.equals(222)) {

					Subscriber cTrans = subscriberMapper.findSubcriberById(tXid);

					if (cTrans == null) {

						logger.info("[Subcriber - Add Data] Error Data is null");
						responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
						responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99Desc);

					} else {

						logger.info("[Subcriber - Add Data] Success");
						responseObjectSubscriber.setRescode(ConstantCode.ErrCode00);
						responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode00Desc);

						responseJsonSubscriber.setId(tXid);
						responseJsonSubscriber.setNama(cTrans.getNama());
						responseJsonSubscriber.setEmail(cTrans.getEmail());
						responseJsonSubscriber.setNohp(cTrans.getNohp());
						responseJsonSubscriber.setTipe(cTrans.getTipe());

						responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
						responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

						return responseSubscriber;
					}

				} else {

					logger.info("[Subcriber - Add Data] Error when input table subcriber.");
					responseObjectSubscriber.setRescode(ConstantCode.ErrCode63);
					responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode63Desc);

				}

			} else {

				responseObjectSubscriber.setRescode(responseCode);
				responseObjectSubscriber.setRescodedesc(responseDesc);

			}

			responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
			responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

			return responseSubscriber;

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("[Subcriber - Add Data] Other Error");
			responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
			responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99Desc);
			responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
			responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

			return responseSubscriber;
		}

	}

	@RequestMapping(value = "/getData")
	public ResponseSubscriber getData(@RequestBody String body) {

		logger.info("[Subcriber - Get Data] Start Get Data");

		RequestSubscriber requestSubscriber = new RequestSubscriber();

		ResponseSubscriber responseSubscriber = new ResponseSubscriber();
		ResponseObjectSubscriber responseObjectSubscriber = new ResponseObjectSubscriber();
		ResponseJsonSubscriber responseJsonSubscriber = new ResponseJsonSubscriber();

		ObjectMapper mapper = new ObjectMapper();

		try {
			requestSubscriber = mapper.readValue(body, RequestSubscriber.class);

			Subscriber cTrans = subscriberMapper.findSubcriberById(requestSubscriber.getId());

			if (cTrans != null) {

				logger.info("[Subcriber - Get Data] Success");
				responseObjectSubscriber.setRescode(ConstantCode.ErrCode00);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode00Desc);

				responseJsonSubscriber.setId(cTrans.getId());
				responseJsonSubscriber.setNama(cTrans.getNama());
				responseJsonSubscriber.setEmail(cTrans.getEmail());
				responseJsonSubscriber.setNohp(cTrans.getNohp());
				responseJsonSubscriber.setTipe(cTrans.getTipe());

				responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
				responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

				return responseSubscriber;

			} else {

				responseObjectSubscriber.setRescode(ConstantCode.ErrCode56);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode56Desc);

			}

			responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
			responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

			return responseSubscriber;

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("[Subcriber - Get Data] Other Error");
			responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
			responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99Desc);
			responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
			responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

			return responseSubscriber;
		}

	}

	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	public ResponseObjectSubscriberDelete deleteData(@RequestBody String body) {

		logger.info("[Subcriber - Delete Data] Start Delete Data");

		RequestSubscriber requestSubscriber = new RequestSubscriber();

		ResponseObjectSubscriberDelete responseObjectSubscriber = new ResponseObjectSubscriberDelete();

		ObjectMapper mapper = new ObjectMapper();

		try {

			requestSubscriber = mapper.readValue(body, RequestSubscriber.class);

			Boolean validate = validateDelete(requestSubscriber);

			if (validate) {

				Integer iTrans = delete(requestSubscriber);

				if (iTrans.equals(222)) {

					logger.info("[Subcriber - Delete Data] Success");
					responseObjectSubscriber.setRescode(ConstantCode.ErrCode00);
					responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode00Desc);

					return responseObjectSubscriber;

				} else {

					logger.info("[Subcriber - Delete Data] Error when update table subcriber.");
					responseObjectSubscriber.setRescode(ConstantCode.ErrCode63);
					responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode63Desc);

				}

			} else {

				responseObjectSubscriber.setRescode(responseCode);
				responseObjectSubscriber.setRescodedesc(responseDesc);

			}

			return responseObjectSubscriber;

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("[Subcriber - Delete Data] Other Error");
			responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
			responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99Desc);

			return responseObjectSubscriber;
		}
	}

	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
	public ResponseSubscriber updateData(@RequestBody String body) {

		logger.info("[Subcriber - Update Data] Start Update Data");

		RequestSubscriber requestSubscriber = new RequestSubscriber();

		ResponseSubscriber responseSubscriber = new ResponseSubscriber();
		ResponseObjectSubscriber responseObjectSubscriber = new ResponseObjectSubscriber();
		ResponseJsonSubscriber responseJsonSubscriber = new ResponseJsonSubscriber();

		ObjectMapper mapper = new ObjectMapper();

		try {

			requestSubscriber = mapper.readValue(body, RequestSubscriber.class);

			Boolean validate = validate(requestSubscriber);

			if (validate) {

				Integer iTrans = update(requestSubscriber);

				if (iTrans.equals(222)) {

					Subscriber cTrans = subscriberMapper.findSubcriberById(requestSubscriber.getId());

					if (cTrans == null) {

						logger.info("[Subcriber - Update Data] Error Data is null");
						responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
						responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99Desc);

					} else {

						logger.info("[Subcriber - Update Data] Success");
						responseObjectSubscriber.setRescode(ConstantCode.ErrCode00);
						responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode00Desc);

						responseJsonSubscriber.setId(cTrans.getId());
						responseJsonSubscriber.setNama(cTrans.getNama());
						responseJsonSubscriber.setEmail(cTrans.getEmail());
						responseJsonSubscriber.setNohp(cTrans.getNohp());
						responseJsonSubscriber.setTipe(cTrans.getTipe());

						responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
						responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

						return responseSubscriber;
					}

				} else {

					logger.info("[Subcriber - Update Data] Error when update table subcriber.");
					responseObjectSubscriber.setRescode(ConstantCode.ErrCode63);
					responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode63Desc);

				}

			} else {

				responseObjectSubscriber.setRescode(responseCode);
				responseObjectSubscriber.setRescodedesc(responseDesc);

			}

			responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
			responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

			return responseSubscriber;

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("[Subcriber - Update Data] Other Error");
			responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
			responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99Desc);
			responseSubscriber.setObjectSubscriber(responseObjectSubscriber);
			responseSubscriber.setJsonSubscriber(responseJsonSubscriber);

			return responseSubscriber;
		}
	}

	// ============================================== FUNCTION DATABASE =============================================

	public Integer insert(RequestSubscriber requestSubscriber) {

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

			logger.info("[Subcriber - Insert] insert to subcriber : " + inp);

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

					logger.info("[Subcriber - Insert] insert to subcriber fail");
					return 111;
				}
			}

			logger.info("[Subcriber - Insert] insert to subcriber success.");
			return 222;

		} catch (Exception e) {

			logger.info("[Subcriber - Insert] insert to subcriber fail");
			e.printStackTrace();
			return 111;

		}
	}

	public Integer update(RequestSubscriber requestSubscriber) {

		try {

			HashMap<String, Object> inp = new HashMap<String, Object>();
			HashMap<String, Object> inp1 = new HashMap<String, Object>();

			inp.put("id", requestSubscriber.getId());
			inp.put("nama", requestSubscriber.getNama());
			inp.put("email", requestSubscriber.getEmail());
			inp.put("nohp", requestSubscriber.getNohp());
			inp.put("tipe", requestSubscriber.getTipe());

			logger.info("[Subcriber - Update] update to subcriber : " + inp);

			Subscriber cTrans = subscriberMapper.findSubcriber(inp);

			if (cTrans != null) {

				inp1.put("id", cTrans.getId());
				inp1.put("nama", requestSubscriber.getNama());
				inp1.put("email", requestSubscriber.getEmail());
				inp1.put("nohp", requestSubscriber.getNohp());
				inp1.put("tipe", requestSubscriber.getTipe());

				Integer sel1 = subscriberMapper.updateSubcriber(inp1);

				if (sel1.equals(1) == false) {

					logger.info("[Subcriber - Update] update to subcriber fail");
					return 111;
				}
			}

			logger.info("[Subcriber - Update] update to subcriber success.");
			return 222;

		} catch (Exception e) {

			logger.info("[Subcriber - Update] update to subcriber fail");
			e.printStackTrace();
			return 111;

		}
	}

	public Integer delete(RequestSubscriber requestSubscriber) {

		try {

			HashMap<String, Object> inp = new HashMap<String, Object>();
			HashMap<String, Object> inp1 = new HashMap<String, Object>();

			inp.put("id", requestSubscriber.getId());
			inp.put("nama", requestSubscriber.getNama());

			logger.info("[Subcriber - Delete] delete to subcriber : " + inp);

			Subscriber cTrans = subscriberMapper.findSubcriberByIdnName(inp);

			if (cTrans != null) {

				inp1.put("id", cTrans.getId());
				inp1.put("nama", cTrans.getNama());

				Integer sel1 = subscriberMapper.deleteSubcriberByTxId(inp1);

				if (sel1.equals(1) == true) {

					logger.info("[Subcriber - Delete] delete to subcriber success");
					return 222;
				}
			}

			logger.info("[Subcriber - Delete] delete to subcriber fail.");
			return 111;

		} catch (Exception e) {

			logger.info("[Subcriber - Delete] delete to subcriber fail");
			e.printStackTrace();
			return 111;

		}
	}
}
