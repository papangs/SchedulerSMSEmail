package com.pank.scheduler.scheduler.entity.params;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestGroupParam {

	@JsonProperty("group_param")
	private String group_param;

	public String getGroup_param() {
		return group_param;
	}

	public void setGroup_param(String group_param) {
		this.group_param = group_param;
	}

	
}
