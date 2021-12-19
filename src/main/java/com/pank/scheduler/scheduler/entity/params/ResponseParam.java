package com.pank.scheduler.scheduler.entity.params;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseParam {

	private ResponseObjectParam objectSetting;

	@JsonProperty("jsonSetting")
	private List<ResponseJsonParam> jsonSetting;

	public ResponseObjectParam getObjectSetting() {
		return objectSetting;
	}

	public void setObjectSetting(ResponseObjectParam objectSetting) {
		this.objectSetting = objectSetting;
	}

	public List<ResponseJsonParam> getJsonSetting() {
		return jsonSetting;
	}

	public void setJsonSetting(List<ResponseJsonParam> jsonSetting) {
		this.jsonSetting = jsonSetting;
	}

	
	
}
