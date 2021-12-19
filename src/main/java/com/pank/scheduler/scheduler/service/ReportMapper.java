package com.pank.scheduler.scheduler.service;

import org.apache.ibatis.annotations.Mapper;
import com.pank.scheduler.scheduler.entity.master.Reporting;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ReportMapper {

	public List<Reporting> findReportAll(HashMap<String, Object> params) throws Exception;

	public List<Reporting> findReportTipe(HashMap<String, Object> params) throws Exception;

	public List<Reporting> findReportStatus(HashMap<String, Object> params) throws Exception;

	public List<Reporting> findReportTipeStatus(HashMap<String, Object> params) throws Exception;
}
