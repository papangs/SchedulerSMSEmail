package com.pank.scheduler.scheduler.entity.params;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestParam {

	@JsonProperty("requestGroupParam")
	private RequestGroupParam requestGroupParam;

	@JsonProperty("requestKodeParam")
	private List<RequestKodeParam> requestKodeParam;

	public RequestGroupParam getRequestGroupParam() {
		return requestGroupParam;
	}

	public void setRequestGroupParam(RequestGroupParam requestGroupParam) {
		this.requestGroupParam = requestGroupParam;
	}

	public List<RequestKodeParam> getRequestKodeParam() {
		return requestKodeParam;
	}

	public void setRequestKodeParam(List<RequestKodeParam> requestKodeParam) {
		this.requestKodeParam = requestKodeParam;
	}

	
}
