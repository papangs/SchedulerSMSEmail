package com.scheduler.entity.subcriber;

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

	@JsonProperty("type")
	@NotNull
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
