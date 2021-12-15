package com.pank.scheduler.scheduler.entity.subcriber;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestSubscriber {

	@JsonProperty("id")
	@NotNull
	private String id;

	@JsonProperty("nama")
	@NotNull
	private String nama;

	@JsonProperty("email")
	@NotNull
	private String email;

	@JsonProperty("nohp")
	@NotNull
	private String nohp;

	@JsonProperty("tipe")
	@NotNull
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
