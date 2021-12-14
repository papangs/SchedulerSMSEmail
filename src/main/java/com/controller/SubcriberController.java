package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.constant.ConstantCode;
import com.constant.ConstantValidation;
import com.entity.subcriber.RequestSubscriber;
import com.entity.subcriber.ResponseJsonSubscriber;
import com.entity.subcriber.ResponseObjectSubscriber;
import com.entity.subcriber.ResponseSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/subcriber")
public class SubcriberController {

	Logger logger = LoggerFactory.getLogger(SubcriberController.class);
	
	@RequestMapping(value = "/addData", method = RequestMethod.GET)
	public ResponseSubscriber addData(@RequestBody String body) {

		logger.info("[Scheaduler - ] Start Add Data");
		
		RequestSubscriber requestSubscriber = new RequestSubscriber();
		
		ResponseSubscriber responseSubscriber = new ResponseSubscriber();
		ResponseObjectSubscriber responseObjectSubscriber = new ResponseObjectSubscriber();
		ResponseJsonSubscriber responseJsonSubscriber = new ResponseJsonSubscriber();
		List<ResponseJsonSubscriber> responseJsonSubscribers = new ArrayList<ResponseJsonSubscriber>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			requestSubscriber = mapper.readValue(body, RequestSubscriber.class);

			if (requestSubscriber.getNama().equals("") || requestSubscriber.getNama().isEmpty() || requestSubscriber.getNama().equals(null)) {

				responseObjectSubscriber.setRescode(ConstantCode.ErrCode01);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode01Desc);
				
			}else if(requestSubscriber.getEmail().equals("") || requestSubscriber.getEmail().isEmpty() || requestSubscriber.getEmail().equals(null)) {

				responseObjectSubscriber.setRescode(ConstantCode.ErrCode02);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode02Desc);
				
			}else if(requestSubscriber.getNohp().equals("") || requestSubscriber.getNohp().isEmpty() || requestSubscriber.getNohp().equals(null)) {

				responseObjectSubscriber.setRescode(ConstantCode.ErrCode03);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode03Desc);
				
			}else if(requestSubscriber.getType().equals("") || requestSubscriber.getType().isEmpty() || requestSubscriber.getType().equals(null)) {

				responseObjectSubscriber.setRescode(ConstantCode.ErrCode04);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode04Desc);
				
			}else if(!requestSubscriber.getType().equals(ConstantValidation.ValSMS) || !requestSubscriber.getType().equals(ConstantValidation.ValEmail)) {

				responseObjectSubscriber.setRescode(ConstantCode.ErrCode05);
				responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode05Desc);
			
			}else {
				
				
				
			}
			
			responseSubscriber.setObject(responseObjectSubscriber);
			responseSubscriber.setJson(responseJsonSubscriber);
			
			return responseSubscriber;
			
		} catch (Exception e) {

			responseObjectSubscriber.setRescode(ConstantCode.ErrCode99);
			responseObjectSubscriber.setRescodedesc(ConstantCode.ErrCode99);
			responseSubscriber.setObject(responseObjectSubscriber);
			responseSubscriber.setJson(responseJsonSubscriber);

			return responseSubscriber;
		}
		
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
	
	
	
	//============================================== FUNCTION DATABASE =============================================
	
	public Integer insertTrans(RequestSubscriber requestSubscriber) {

		try {
			
			HashMap<String, Object> inp = new HashMap<String, Object>();
			HashMap<String, Object> inp1 = new HashMap<String, Object>();
			HashMap<String, Object> inp2 = new HashMap<String, Object>();
			HashMap<String, Object> inp3 = new HashMap<String, Object>();
			HashMap<String, Object> inps = new HashMap<String, Object>();
			ResTKR resTKR = new ResTKR();

			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("mmss");

			Random randomInt = new Random();
			Integer intRand = randomInt.nextInt(999999);
			String strRand = String.format("%6s", intRand).replace(' ', '0');
			
			Integer intRands = randomInt.nextInt(999999999);
			String strRands = String.format("%10s", intRands).replace(' ', '0');

			TotalTagAir = pelanggan.gettAGIHAN()+ pelanggan.getmATERAI();
			TotalDenda = pelanggan.getdENDA();
			
			logger.info("[inq-pam-air-tkr] INSERT ADMINFEE : "+TotalAdminFee);
			
			TotalSettlement = pelanggan.gettAGIHAN() + pelanggan.getdENDA() + pelanggan.getmATERAI();

			String TRXID = "PDAMTKR001" + dateFormat.format(date) + strRand;
			
			inp.put("TXID", TRXID);
			inp.put("PRE_VACCT_NO", "FM" + dateFormat2.format(date) + strRands + pelanggan.getpERIODE());
			inp.put("I_MID", "PDAMTKR001");
			inp.put("PAY_METHOD", "01");
			inp.put("STATUS", "03");
			inp.put("CURRENCY", "IDR");
			inp.put("AMT", TotalTagAir.toString());
			inp.put("AMT_ADM", TotalAdminFee);
			inp.put("AMT_PENALTY", TotalDenda.toString());
			inp.put("AMT_SETTL", TotalSettlement);
			inp.put("REFERENCE_NO", retnum);
			inp.put("BILLING_ID", reqInqPamAir.getCustomerID());
			inp.put("BILLING_NM", resTKR.getdATA().getnAMA().toString());
			inp.put("BILLING_ADDRESS", resTKR.getdATA().getaLAMAT().toString());
			inp.put("BANK_CD", ConstantParam.PdamTkr);
			inp.put("VACCT_NO", reqInqPamAir.getCustomerID());
			inp.put("BILL_REPEAT", bill_reapeat);
			inp.put("CHANNEL_TYPE", "6012");
			inp.put("PROC_SVR", serverPort);
			inp.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
			inp.put("CA_CODE", reqInqPamAir.getCaId().toString());
			inp.put("OLD_TRX_DATA", "0");//PERMINTAAN ESTU
			inp.put("REFUND_TM", "0");//JENIS TAGIHAN
			inp.put("ADDITIONALDATA", "0");
			
			String trxData = mapper.writeValueAsString(resTKR.getdATA());
			inp.put("TRX_DATA", trxData);

			logger.info("[inq-pam-air-tkr] insert to tb_trans : " + inp);

			TbTrans cTrans = tbTransMapper.findTxVacctNo(inp);

			if (cTrans == null) {
				
				inp1.put("TXID", TRXID);
				inp1.put("PRE_VACCT_NO", "FM" + dateFormat2.format(date) + strRands + pelanggan.getpERIODE());
				inp1.put("I_MID", "PDAMTKR001");
				inp1.put("PAY_METHOD", "01");
				inp1.put("STATUS", "03");
				inp1.put("CURRENCY", "IDR");
				inp1.put("AMT", TotalTagAir.toString());
				inp1.put("AMT_ADM", TotalAdminFee);
				inp1.put("AMT_PENALTY", TotalDenda.toString());
				inp1.put("AMT_SETTL", TotalSettlement);
				inp1.put("REFERENCE_NO", retnum);
				inp1.put("BILLING_ID", reqInqPamAir.getCustomerID());
				inp1.put("BILLING_NM", resTKR.getdATA().getnAMA().toString());
				inp1.put("BILLING_ADDRESS", resTKR.getdATA().getaLAMAT().toString());
				inp1.put("BANK_CD", ConstantParam.PdamTkr);
				inp1.put("VACCT_NO", reqInqPamAir.getCustomerID());
				inp1.put("BILL_REPEAT", bill_reapeat);
				inp1.put("CHANNEL_TYPE", "6012");
				inp1.put("PROC_SVR", serverPort);
				inp1.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
				inp1.put("CA_CODE", reqInqPamAir.getCaId());
				inp1.put("OLD_TRX_DATA", "0");//PERMINTAAN ESTU
				inp1.put("REFUND_TM", "0");//JENIS TAGIHAN
				inp1.put("ADDITIONALDATA", "0");
				String trxData1 = mapper.writeValueAsString(resTKR.getdATA());
				inp1.put("TRX_DATA", trxData1);
				
				Integer sel1 = tbTransMapper.insertTxId(inp1);
				tXid = inp1.get("TXID").toString();
				
				if (sel1.equals(1) == false) {
					
					logger.info("[inq-pam-air-tkr] insert to tb_trans fail");
					return 111;
					
				}
				
			} else {
				
				if(cTrans.getStatus().equals("04")) {

					inps.put("TXID", TRXID);
					inps.put("TXID_OLD", cTrans.getTxid());
					inps.put("I_MID", "PDAMTKR001");
					inps.put("PAY_METHOD", "01");
					inps.put("STATUS", "03");
					inps.put("CURRENCY", "IDR");
					inps.put("AMT", TotalTagAir.toString());
					inps.put("REFERENCE_NO", retnum);
					inps.put("BILLING_ID", reqInqPamAir.getCustomerID());
					inps.put("BILLING_NM", resTKR.getdATA().getnAMA().toString());
					inps.put("BILLING_ADDRESS", resTKR.getdATA().getaLAMAT().toString());
					inps.put("BANK_CD", ConstantParam.PdamTkr);
					inps.put("VACCT_NO", reqInqPamAir.getCustomerID());
					inps.put("BILL_REPEAT", bill_reapeat);
					inps.put("CHANNEL_TYPE", "6012");
					inps.put("PROC_SVR", serverPort);
					inps.put("AMT_ADM", TotalAdminFee);
					inps.put("AMT_PENALTY", TotalDenda.toString());
					inps.put("AMT_SETTL", TotalSettlement);
					inps.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
					inps.put("PRE_VACCT_NO", "FM" + dateFormat2.format(date) + strRands + pelanggan.getpERIODE());
					inps.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
					inps.put("CA_CODE", reqInqPamAir.getCaId());
					inps.put("OLD_TRX_DATA", "0");//PERMINTAAN ESTU
					inps.put("REFUND_TM", "0");//JENIS TAGIHAN
					inps.put("ADDITIONALDATA", "0");
					String trxData2 = mapper.writeValueAsString(resTKR.getdATA());
					inps.put("TRX_DATA", trxData2);
					
					Integer sels = tbTransMapper.updateTxId0403(inps);
					tXid = inps.get("TXID").toString();

					if (sels.equals(1) == false) {

						logger.info("[inq-pam-air-tkr] insert to tb_trans fail");
						return 111;

					}
					
				}else if(cTrans.getStatus().equals("03")) {

					inp2.put("TXID", TRXID);
					inp2.put("TXID_OLD", cTrans.getTxid());
					inp2.put("I_MID", "PDAMTKR001");
					inp2.put("PAY_METHOD", "01");
					inp2.put("STATUS", "03");
					inp2.put("CURRENCY", "IDR");
					inp2.put("AMT", TotalTagAir.toString());
					inp2.put("REFERENCE_NO", retnum);
					inp2.put("BILLING_ID", reqInqPamAir.getCustomerID());
					inp2.put("BILLING_NM", resTKR.getdATA().getnAMA().toString());
					inp2.put("BILLING_ADDRESS", resTKR.getdATA().getaLAMAT().toString());
					inp2.put("BANK_CD", ConstantParam.PdamTkr);
					inp2.put("VACCT_NO", reqInqPamAir.getCustomerID());
					inp2.put("BILL_REPEAT", bill_reapeat);
					inp2.put("CHANNEL_TYPE", "6012");
					inp2.put("PROC_SVR", serverPort);
					inp2.put("AMT_ADM", TotalAdminFee);
					inp2.put("AMT_PENALTY", TotalDenda.toString());
					inp2.put("AMT_SETTL", TotalSettlement);
					inp2.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
					inp2.put("PRE_VACCT_NO", "FM" + dateFormat2.format(date) + strRands + pelanggan.getpERIODE());
					inp2.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
					inp2.put("STATUS", "03");
					inp2.put("CA_CODE", reqInqPamAir.getCaId());
					inp2.put("OLD_TRX_DATA", "0");//PERMINTAAN ESTU
					inp2.put("REFUND_TM", "0");//JENIS TAGIHAN
					inp2.put("ADDITIONALDATA", "0");
					String trxData3 = mapper.writeValueAsString(resTKR.getdATA());
					inp2.put("TRX_DATA", trxData3);
					
					Integer sels = tbTransMapper.updateTxIdd(inp2);
					tXid = inp2.get("TXID").toString();

					if (sels.equals(1) == false) {

						logger.info("[inq-pam-air-tkr] update to tb_trans fail");
						return 111;

					}
					
				}else if(cTrans.getStatus().equals("00")) {

					tXid = cTrans.getTxid();
					logger.info("[inq-pam-air-tkr] insert to tb_trans success.");
					return 222;
					
				}else if(cTrans.getStatus().equals("02")){

					inp3.put("TXID", TRXID);
					inp3.put("TXID_OLD", cTrans.getTxid());
					inp3.put("I_MID", "PDAMTKR001");
					inp3.put("PAY_METHOD", "01");
					inp3.put("STATUS", "03");
					inp3.put("CURRENCY", "IDR");
					inp3.put("AMT", TotalTagAir.toString());
					inp3.put("REFERENCE_NO", retnum);
					inp3.put("BILLING_ID", reqInqPamAir.getCustomerID());
					inp3.put("BILLING_NM", resTKR.getdATA().getnAMA().toString());
					inp3.put("BILLING_ADDRESS", resTKR.getdATA().getaLAMAT().toString());
					inp3.put("BANK_CD", ConstantParam.PdamTkr);
					inp3.put("VACCT_NO", reqInqPamAir.getCustomerID());
					inp3.put("BILL_REPEAT", bill_reapeat);
					inp3.put("CHANNEL_TYPE", "6012");
					inp3.put("PROC_SVR", serverPort);
					inp3.put("AMT_ADM", TotalAdminFee);
					inp3.put("AMT_PENALTY", TotalDenda.toString());
					inp3.put("AMT_SETTL", TotalSettlement);
					inp3.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
					inp3.put("PRE_VACCT_NO", "FM" + dateFormat2.format(date) + strRands + pelanggan.getpERIODE());
					inp3.put("PAYMENT_PERIODE", pelanggan.getpERIODE().toString());
					inp3.put("CA_CODE", reqInqPamAir.getCaId());
					inp3.put("OLD_TRX_DATA", "0");//PERMINTAAN ESTU
					inp3.put("REFUND_TM", "0");//JENIS TAGIHAN
					inp3.put("ADDITIONALDATA", "0");
					String trxData4 = mapper.writeValueAsString(resTKR.getdATA());
					inp3.put("TRX_DATA", trxData4);
					
					Integer sels = tbTransMapper.updateTxId0203(inp3);
					tXid = inp3.get("TXID").toString();

					if (sels.equals(1) == false) {

						logger.info("[inq-pam-air-tkr] update to tb_trans fail");
						return 111;

					}
					
				}
				
			}


			logger.info("[inq-pam-air-tkr] insert to tb_trans success.");
			return 222;

		} catch (Exception e) {
			
			logger.info("[inq-pam-air-tkr] insert to tb_trans fail");
			e.printStackTrace();
			return 111;
			
		}
	}
}
