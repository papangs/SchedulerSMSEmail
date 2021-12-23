package com.pank.scheduler.scheduler.config;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CommonRs<T> {
 
    @JsonInclude(JsonInclude.Include.NON_NULL)
	
    protected int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected T data;

    public CommonRs(int status, String message) {
            
        super();
        this.status = status;
        this.message = message;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

    
}
