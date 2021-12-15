package com.scheduler.entity.subcriber;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSubscriber {

	private ResponseObjectSubscriber object;

	@JsonProperty("JsonData")
	private ResponseJsonSubscriber JsonData;

	public ResponseObjectSubscriber getObject() {
		return object;
	}

	public void setObject(ResponseObjectSubscriber object) {
		this.object = object;
	}

	public ResponseJsonSubscriber getJsonData() {
		return JsonData;
	}

	public void setJsonData(ResponseJsonSubscriber jsonData) {
		JsonData = jsonData;
	}

	
}
