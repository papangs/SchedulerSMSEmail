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
import com.pank.scheduler.scheduler.constant.ConstantValidasi;
import com.pank.scheduler.scheduler.entity.master.Reporting;
import com.pank.scheduler.scheduler.entity.report.RequestReport;
import com.pank.scheduler.scheduler.entity.report.ResponseJsonReport;
import com.pank.scheduler.scheduler.entity.report.ResponseObjectReport;
import com.pank.scheduler.scheduler.entity.report.ResponseReport;
import com.pank.scheduler.scheduler.service.ReportMapper;

@RestController
@RequestMapping("/")
public class ReportingController {

	Logger logger = LoggerFactory.getLogger(ReportingController.class);

	@Autowired
	private ReportMapper reportMapper;

	private String responseCode = null;
	private String responseDesc = null;
	
	public boolean validate(RequestReport requestParam) {

		try {

			if (requestParam.getDari_tanggal().equals("") || requestParam.getDari_tanggal().isEmpty()
					|| requestParam.getDari_tanggal().equals(null)) {

				logger.info("[Setting - Validate Params] Error Group Param");
				responseCode = ConstantCode.ErrCode09;
				responseDesc = ConstantCode.ErrCode09Desc;
				return false;

			}else if (requestParam.getSampai_tanggal().equals("") || requestParam.getSampai_tanggal().isEmpty()
					|| requestParam.getSampai_tanggal().equals(null)) {

				logger.info("[Setting - Validate Params] Error Group Param");
				responseCode = ConstantCode.ErrCode10;
				responseDesc = ConstantCode.ErrCode10Desc;
				return false;

			} else if (requestParam.getChanel_pengiriman().equals("") || requestParam.getChanel_pengiriman().isEmpty()
					|| requestParam.getChanel_pengiriman().equals(null)) {

				logger.info("[Setting - Validate Params] Error Group Param");
				responseCode = ConstantCode.ErrCode11;
				responseDesc = ConstantCode.ErrCode11Desc;
				return false;

			} else if (requestParam.getStatus().equals("") || requestParam.getStatus().isEmpty()
					|| requestParam.getStatus().equals(null)) {

				logger.info("[Setting - Validate Params] Error Group Param");
				responseCode = ConstantCode.ErrCode12;
				responseDesc = ConstantCode.ErrCode12Desc;
				return false;

			}
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@RequestMapping(value = "reporting", method = RequestMethod.POST)
	public ResponseReport addData(@RequestBody String body) {

		logger.info("[Reporting] Start Report");
		
		RequestReport requestReport = new RequestReport();

		ResponseReport responseReport = new ResponseReport();
		ResponseObjectReport responseObjectReport = new ResponseObjectReport();
		List<ResponseJsonReport> responseJsonReports = new ArrayList<ResponseJsonReport>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {

			requestReport = mapper.readValue(body, RequestReport.class);

			Boolean validate = validate(requestReport);

			if (validate) {
				
				HashMap<String, Object> has = new HashMap<String, Object>();
				has.put("tanggal_kirim1", requestReport.getDari_tanggal());
				has.put("tanggal_kirim2", requestReport.getSampai_tanggal());
				
				List<Reporting> cTrans = null;
				
				if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValEMAIL) && requestReport.getStatus().equals(ConstantValidasi.ValALL)) {

					has.put("tipe", requestReport.getChanel_pengiriman());
					cTrans = reportMapper.findReportTipe(has);
					
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValSMS) && requestReport.getStatus().equals(ConstantValidasi.ValALL)) {

					has.put("tipe", requestReport.getChanel_pengiriman());
					cTrans = reportMapper.findReportTipe(has);
					
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValALL) && requestReport.getStatus().equals(ConstantValidasi.ValSUKSES)) {

					has.put("status", requestReport.getStatus());
					cTrans = reportMapper.findReportStatus(has);
					
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValALL) && requestReport.getStatus().equals(ConstantValidasi.ValGAGAL)) {

					has.put("status", requestReport.getStatus());
					cTrans = reportMapper.findReportStatus(has);
					
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValEMAIL) && requestReport.getStatus().equals(ConstantValidasi.ValSUKSES)) {

					has.put("tipe", requestReport.getChanel_pengiriman());
					has.put("status", requestReport.getStatus());
					cTrans = reportMapper.findReportTipeStatus(has);
				
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValEMAIL) && requestReport.getStatus().equals(ConstantValidasi.ValGAGAL)) {

					has.put("tipe", requestReport.getChanel_pengiriman());
					has.put("status", requestReport.getStatus());
					cTrans = reportMapper.findReportTipeStatus(has);
				
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValSMS) && requestReport.getStatus().equals(ConstantValidasi.ValSUKSES)) {

					has.put("tipe", requestReport.getChanel_pengiriman());
					has.put("status", requestReport.getStatus());
					cTrans = reportMapper.findReportTipeStatus(has);
				
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValSMS) && requestReport.getStatus().equals(ConstantValidasi.ValGAGAL)) {

					has.put("tipe", requestReport.getChanel_pengiriman());
					has.put("status", requestReport.getStatus());
					cTrans = reportMapper.findReportTipeStatus(has);
				
				}else if (requestReport.getChanel_pengiriman().equals(ConstantValidasi.ValALL) && requestReport.getStatus().equals(ConstantValidasi.ValALL)) {

					cTrans = reportMapper.findReportAll(has);
				
				}

				if (cTrans == null || cTrans.isEmpty()) {
					
					logger.info("[Reporting] Error Data is null");
					responseObjectReport.setRescode(ConstantCode.ErrCode99);
					responseObjectReport.setRescodedesc(ConstantCode.ErrCode99Desc);

					responseObjectReport.setDari_tanggal(requestReport.getDari_tanggal());
					responseObjectReport.setSampai_tanggal(requestReport.getSampai_tanggal());
					responseObjectReport.setChanel_pengiriman(requestReport.getChanel_pengiriman());
					responseObjectReport.setStatus(requestReport.getStatus());
					
				}else {
					
					logger.info("[Reporting] Success");
					responseObjectReport.setRescode(ConstantCode.ErrCode00);
					responseObjectReport.setRescodedesc(ConstantCode.ErrCode00Desc);

					responseObjectReport.setDari_tanggal(requestReport.getDari_tanggal());
					responseObjectReport.setSampai_tanggal(requestReport.getSampai_tanggal());
					responseObjectReport.setChanel_pengiriman(requestReport.getChanel_pengiriman());
					responseObjectReport.setStatus(requestReport.getStatus());
					
					for (Reporting res : cTrans) {
						
						ResponseJsonReport responseJsonReport = new ResponseJsonReport();
						
						responseJsonReport.setId(res.getId_subcriber());
						responseJsonReport.setNama(res.getSubscriber().getNama());
						responseJsonReport.setTipe(res.getSubscriber().getTipe());
						responseJsonReport.setEmail(res.getSubscriber().getEmail());
						responseJsonReport.setNohp(res.getSubscriber().getNohp());
						responseJsonReport.setTanggal_jam_kirim(res.getTanggal_kirim()+" "+res.getJam_kirim());
						responseJsonReport.setStatus(res.getStatus());
						
						responseJsonReports.add(responseJsonReport);
						
					}
					
					responseReport.setObjectReport(responseObjectReport);
					responseReport.setJsonReport(responseJsonReports);

					return responseReport;
					
				}
				
			}else {

				responseObjectReport.setRescode(responseCode);
				responseObjectReport.setRescodedesc(responseDesc);

				responseObjectReport.setDari_tanggal(requestReport.getDari_tanggal());
				responseObjectReport.setSampai_tanggal(requestReport.getSampai_tanggal());
				responseObjectReport.setChanel_pengiriman(requestReport.getChanel_pengiriman());
				responseObjectReport.setStatus(requestReport.getStatus());
				
			}

			responseReport.setObjectReport(responseObjectReport);
			responseReport.setJsonReport(responseJsonReports);
			
			return responseReport;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.info("[Reporting] Other Error");
			responseObjectReport.setRescode(ConstantCode.ErrCode99);
			responseObjectReport.setRescodedesc(ConstantCode.ErrCode99Desc);

			responseObjectReport.setDari_tanggal(requestReport.getDari_tanggal());
			responseObjectReport.setSampai_tanggal(requestReport.getSampai_tanggal());
			responseObjectReport.setChanel_pengiriman(requestReport.getChanel_pengiriman());
			responseObjectReport.setStatus(requestReport.getStatus());
			
			responseReport.setObjectReport(responseObjectReport);
			responseReport.setJsonReport(responseJsonReports);
			
			return responseReport;
		}
		
	}
	
}
