package com.pank.scheduler.scheduler.entity.setting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSetting {

	private ResponseObjectSetting objectSetting;

	@JsonProperty("jsonSetting")
	private ResponseJsonSetting jsonSetting;

	public ResponseObjectSetting getObjectSetting() {
		return objectSetting;
	}

	public void setObjectSetting(ResponseObjectSetting objectSetting) {
		this.objectSetting = objectSetting;
	}

	public ResponseJsonSetting getJsonSetting() {
		return jsonSetting;
	}

	public void setJsonSetting(ResponseJsonSetting jsonSetting) {
		this.jsonSetting = jsonSetting;
	}
	
	
}
