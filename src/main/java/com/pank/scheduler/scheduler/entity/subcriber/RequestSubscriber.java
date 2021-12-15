package com.pank.scheduler.scheduler.entity.subcriber;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestSubscriber {

	@JsonProperty("nama")
	private String nama;

	@JsonProperty("email")
	private String email;

	@JsonProperty("nohp")
	private String nohp;

	@JsonProperty("tipe")
	private String tipe;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNohp() {
		return nohp;
	}

	public void setNohp(String nohp) {
		this.nohp = nohp;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
	}

    
}
