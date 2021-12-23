package com.pank.scheduler.scheduler.entity.report;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObjectReport {

	@JsonProperty("rescode")
	@NotNull
	private String rescode;

	@JsonProperty("rescodedesc")
	@NotNull
	private String rescodedesc;

	@JsonProperty("dari_tanggal")
	@NotNull
	private String dari_tanggal;

	@JsonProperty("sampai_tanggal")
	@NotNull
	private String sampai_tanggal;

	@JsonProperty("chanel_pengiriman")
	@NotNull
	private String chanel_pengiriman;

	@JsonProperty("status")
	@NotNull
	private String status;

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

	public String getDari_tanggal() {
		return dari_tanggal;
	}

	public void setDari_tanggal(String dari_tanggal) {
		this.dari_tanggal = dari_tanggal;
	}

	public String getSampai_tanggal() {
		return sampai_tanggal;
	}

	public void setSampai_tanggal(String sampai_tanggal) {
		this.sampai_tanggal = sampai_tanggal;
	}

	public String getChanel_pengiriman() {
		return chanel_pengiriman;
	}

	public void setChanel_pengiriman(String chanel_pengiriman) {
		this.chanel_pengiriman = chanel_pengiriman;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
