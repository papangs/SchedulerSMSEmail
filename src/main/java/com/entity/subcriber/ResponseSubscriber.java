package com.entity.subcriber;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSubscriber {

	@JsonProperty("Object")
	private ResponseObjectSubscriber object;

	@JsonProperty("Json")
	private List<ResponseJsonSubscriber> Json;

	public ResponseObjectSubscriber getObject() {
		return object;
	}

	public void setObject(ResponseObjectSubscriber object) {
		this.object = object;
	}

	public List<ResponseJsonSubscriber> getJson() {
		return Json;
	}

	public void setJson(List<ResponseJsonSubscriber> json) {
		Json = json;
	}

	
	
}
