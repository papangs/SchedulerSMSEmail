package com.entity.subcriber;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObjectSubscriber {

	@JsonProperty("rescode")
	private String rescode;

	@JsonProperty("rescodedesc")
	private String rescodedesc;

	public String getRescode() {
		return rescode;
	}

	public void setRescode(String rescode) {
		this.rescode = rescode;
	}

	public String getRescodedesc() {
		return rescodedesc;
	}

	public void setRescodedesc(String rescodedesc) {
		this.rescodedesc = rescodedesc;
	}
	
	
}
