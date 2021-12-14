package com.entity.subcriber;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSubscriber {

	@JsonProperty("Object")
	private ResponseObjectSubscriber object;

	@JsonProperty("Json")
	private ResponseJsonSubscriber Json;

	public ResponseObjectSubscriber getObject() {
		return object;
	}

	public void setObject(ResponseObjectSubscriber object) {
		this.object = object;
	}

	public ResponseJsonSubscriber getJson() {
		return Json;
	}

	public void setJson(ResponseJsonSubscriber json) {
		Json = json;
	}

	
	
}
