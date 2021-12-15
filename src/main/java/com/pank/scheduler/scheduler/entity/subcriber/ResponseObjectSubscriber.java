package com.pank.scheduler.scheduler.entity.subcriber;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObjectSubscriber {

	@JsonProperty("rescode")
	@NotNull
	private String rescode;

	@JsonProperty("rescodedesc")
	@NotNull
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
