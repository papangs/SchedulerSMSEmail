package com.pank.scheduler.scheduler.entity.subcriber;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSubscriber {

	private ResponseObjectSubscriber objectSubscriber;

	@JsonProperty("jsonSubscriber")
	private ResponseJsonSubscriber jsonSubscriber;

	public ResponseObjectSubscriber getObjectSubscriber() {
		return objectSubscriber;
	}

	public void setObjectSubscriber(ResponseObjectSubscriber objectSubscriber) {
		this.objectSubscriber = objectSubscriber;
	}

	public ResponseJsonSubscriber getJsonSubscriber() {
		return jsonSubscriber;
	}

	public void setJsonSubscriber(ResponseJsonSubscriber jsonSubscriber) {
		this.jsonSubscriber = jsonSubscriber;
	}
	
	
}
