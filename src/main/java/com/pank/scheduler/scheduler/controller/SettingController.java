package com.pank.scheduler.scheduler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pank.scheduler.scheduler.constant.ConstantCode;
import com.pank.scheduler.scheduler.entity.master.SettingParam;
import com.pank.scheduler.scheduler.entity.params.RequestGroupParam;
import com.pank.scheduler.scheduler.entity.params.RequestKodeParam;
import com.pank.scheduler.scheduler.entity.params.RequestParam;
import com.pank.scheduler.scheduler.entity.params.ResponseJsonParam;
import com.pank.scheduler.scheduler.entity.params.ResponseObjectParam;
import com.pank.scheduler.scheduler.entity.params.ResponseParam;
import com.pank.scheduler.scheduler.service.SettingMapper;

@RestController
@RequestMapping("/setting")
public class SettingController {

	Logger logger = LoggerFactory.getLogger(SettingController.class);

	@Autowired
	private SettingMapper settingMapper;
	
	@RequestMapping(value = "/updateParams", method = RequestMethod.POST)
	public ResponseParam settingParams(@RequestBody String body) {

		logger.info("[Setting - Params] Start Setting Params");

		RequestParam requestParam = new RequestParam();
		RequestGroupParam requestGroupParam = new RequestGroupParam();
		List<RequestKodeParam> requestKodeParams = new ArrayList<RequestKodeParam>();

		ResponseParam responseEmail = new ResponseParam();
		ResponseObjectParam responseObjectEmail = new ResponseObjectParam();
		List<ResponseJsonParam> responseJsonEmail = new ArrayList<ResponseJsonParam>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			
			requestParam = mapper.readValue(body, RequestParam.class);

			Integer iTrans = updateParams(requestParam);

			if (iTrans.equals(222)) {

				HashMap<String, Object> inp = new HashMap<String, Object>();

				inp.put("group_param", requestParam.getRequestGroupParam().getGroup_param());
				List<SettingParam> cTrans = settingMapper.findParameterById(inp);

				if (cTrans == null) {
					
					logger.info("[Setting - Params] Error Data is null");
					responseObjectEmail.setRescode(ConstantCode.ErrCode99);
					responseObjectEmail.setRescodedesc(ConstantCode.ErrCode99Desc);
					
				}else {
					
					logger.info("[Setting - Params] Success");
					responseObjectEmail.setRescode(ConstantCode.ErrCode00);
					responseObjectEmail.setRescodedesc(ConstantCode.ErrCode00Desc);

					for (SettingParam res : cTrans) {

						ResponseJsonParam responseJsonParam = new ResponseJsonParam();
						
						responseJsonParam.setId(res.getId());
						responseJsonParam.setGroup_param(res.getGroup_param());
						responseJsonParam.setKode_param(res.getKode_param());
						responseJsonParam.setParam(res.getParam());
						
						responseJsonEmail.add(responseJsonParam);
					}

					responseEmail.setObjectSetting(responseObjectEmail);
					responseEmail.setJsonSetting(responseJsonEmail);

					return responseEmail;
					
				}
				
			}else {

				logger.info("[Setting - Params] Error when update table Setting Params.");
				responseObjectEmail.setRescode(ConstantCode.ErrCode63);
				responseObjectEmail.setRescodedesc(ConstantCode.ErrCode63Desc);
			}
			
			return responseEmail;
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.info("[Setting - Params] Other Error");
			responseObjectEmail.setRescode(ConstantCode.ErrCode99);
			responseObjectEmail.setRescodedesc(ConstantCode.ErrCode99Desc);
			responseEmail.setObjectSetting(responseObjectEmail);
			responseEmail.setJsonSetting(responseJsonEmail);
			return responseEmail;
		}
	}
	
	// ============================================== FUNCTION ============================================================
	
	public Integer updateParams(RequestParam requestEmail) {

		try {

			HashMap<String, Object> inp1 = new HashMap<String, Object>();
			HashMap<String, Object> inp = new HashMap<String, Object>();

			ObjectMapper mapper = new ObjectMapper();

			String hasMpa = mapper.writeValueAsString(requestEmail.getRequestGroupParam());
			HashMap<String, Object> has = mapper.readValue(hasMpa, HashMap.class);
			
			String hasMpas = mapper.writeValueAsString(requestEmail.getRequestKodeParam());
			List<HashMap<String, Object>> hass = mapper.readValue(hasMpas, List.class);

			inp.put("group_param", has.get("group_param"));
			List<SettingParam> cTrans = settingMapper.findParameterById(inp);

			logger.info("[Subcriber - Insert] insert to subcriber : " + inp);
			
			if (cTrans != null) {
				
				for (HashMap<String, Object> res : hass) {

					inp1.put("group_param", has.get("group_param"));
					inp1.put("kode_param", res.get("kode_param"));
					inp1.put("param", res.get("param"));

					Integer sel1 = settingMapper.updateParameter(inp1);

					if (sel1.equals(1) == false) {

						logger.info("[Setting - Params] update to Params fail");
						return 111;
					}
				}
			}

			logger.info("[Setting - Params] update to Params success.");
			return 222;

		} catch (Exception e) {

			logger.info("[Setting - Params] update to Params fail");
			e.printStackTrace();
			return 111;

		}
	}
	
}
