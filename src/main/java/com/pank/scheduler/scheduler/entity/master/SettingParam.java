package com.pank.scheduler.scheduler.entity.master;

import java.io.Serializable;

public class SettingParam implements Serializable{

    private String id;
    private String group_param;
    private String kode_param;
    private String param;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroup_param() {
		return group_param;
	}
	public void setGroup_param(String group_param) {
		this.group_param = group_param;
	}
	public String getKode_param() {
		return kode_param;
	}
	public void setKode_param(String kode_param) {
		this.kode_param = kode_param;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
}
