package com.pank.scheduler.scheduler.service;

import org.apache.ibatis.annotations.Mapper;

import com.pank.scheduler.scheduler.entity.master.Subscriber;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SubscriberMapper {

	public List<Subscriber> findSubcribers() throws Exception;
	
    public Subscriber findSubcriberByIdnName(HashMap<String, Object> params) throws Exception;

    public Subscriber findSubcriberById(String id) throws Exception;
    
	public Subscriber findSubcriber(HashMap<String, Object> params) throws Exception;
    
    public Integer insertSubcriber(HashMap<String, Object> params) throws Exception;

    public Integer updateSubcriber(HashMap<String, Object> params) throws Exception;
    
    public Integer deleteSubcriberByTxId(HashMap<String, Object> params) throws Exception;

}
