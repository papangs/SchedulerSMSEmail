package com.pank.scheduler.scheduler.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pank.scheduler.scheduler.entity.master.Setting;
import java.util.HashMap;

@Mapper
public interface SettingMapper {

	public Setting findParameterById(HashMap<String, Object> params) throws Exception;

    public Integer updateParameter(HashMap<String, Object> params) throws Exception;

    public String getParam(@Param("group_param") String grpParam, @Param("kode_param") String kdParam) throws Exception;
}
