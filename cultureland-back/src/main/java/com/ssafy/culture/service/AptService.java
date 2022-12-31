package com.ssafy.culture.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.culture.dto.AptDeal;
import com.ssafy.culture.dto.AptInfo;

public interface AptService {
	List<AptInfo> getAptInfoByDongCode(long dongCode) throws SQLException;
	List<AptDeal> getAptDealAptCode(long aptCode) throws SQLException;
	
}
