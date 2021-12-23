package com.pank.scheduler.scheduler.entity.report;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseReport {

	private ResponseObjectReport objectReport;

	@JsonProperty("jsonReport")
	private List<ResponseJsonReport> jsonReport;

	public ResponseObjectReport getObjectReport() {
		return objectReport;
	}

	public void setObjectReport(ResponseObjectReport objectReport) {
		this.objectReport = objectReport;
	}

	public List<ResponseJsonReport> getJsonReport() {
		return jsonReport;
	}

	public void setJsonReport(List<ResponseJsonReport> jsonReport) {
		this.jsonReport = jsonReport;
	}

	
}
