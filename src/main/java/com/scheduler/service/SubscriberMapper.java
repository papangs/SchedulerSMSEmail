package com.scheduler.service;

import org.apache.ibatis.annotations.Mapper;

import com.scheduler.entity.master.Subscriber;

import java.util.HashMap;

@Mapper
public interface SubscriberMapper {

    public Subscriber findSubcriberById(String id) throws Exception;
    
	public Subscriber findSubcriber(HashMap<String, Object> params) throws Exception;
    
    public Integer insertSubcriber(HashMap<String, Object> params) throws Exception;

    public Integer updateSubcriber(HashMap<String, Object> params) throws Exception;
    
    public Integer deleteSubcriberByTxId(HashMap<String, Object> params) throws Exception;

}
