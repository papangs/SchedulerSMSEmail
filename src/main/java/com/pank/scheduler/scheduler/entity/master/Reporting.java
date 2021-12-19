package com.pank.scheduler.scheduler.entity.master;

import java.io.Serializable;

public class Reporting implements Serializable{

    private Integer id_report;
    private String id_subcriber;
    private String tanggal_kirim;
    private String jam_kirim;
    private String status;
    private Subscriber subscriber;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTanggal_kirim() {
		return tanggal_kirim;
	}
	public void setTanggal_kirim(String tanggal_kirim) {
		this.tanggal_kirim = tanggal_kirim;
	}
	public String getJam_kirim() {
		return jam_kirim;
	}
	public void setJam_kirim(String jam_kirim) {
		this.jam_kirim = jam_kirim;
	}
	public Subscriber getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}
	public String getId_subcriber() {
		return id_subcriber;
	}
	public void setId_subcriber(String id_subcriber) {
		this.id_subcriber = id_subcriber;
	}
	public Integer getId_report() {
		return id_report;
	}
	public void setId_report(Integer id_report) {
		this.id_report = id_report;
	}
    
    
}
