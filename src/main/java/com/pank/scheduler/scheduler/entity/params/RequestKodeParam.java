package com.pank.scheduler.scheduler.entity.params;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestKodeParam {

	@JsonProperty("kode_param")
	private String kode_param;

	@JsonProperty("param")
	private String param;

	public String getKode_param() {
		return kode_param;
	}

	public void setKode_param(String kode_param) {
		this.kode_param = kode_param;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	
    
}
