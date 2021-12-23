package com.pank.scheduler.scheduler.entity.report;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseJsonReport {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("nama")
	private String nama;

	@JsonProperty("tipe")
	private String tipe;

	@JsonProperty("email")
	private String email;

	@JsonProperty("nohp")
	private String nohp;

	@JsonProperty("tanggal_jam_kirim")
	private String tanggal_jam_kirim;

	@JsonProperty("status")
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
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

	public String getTanggal_jam_kirim() {
		return tanggal_jam_kirim;
	}

	public void setTanggal_jam_kirim(String tanggal_jam_kirim) {
		this.tanggal_jam_kirim = tanggal_jam_kirim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
