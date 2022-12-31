package com.ssafy.culture.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.culture.dto.AptInfo;
import com.ssafy.culture.dto.AptDeal;

@Mapper
public interface AptDao {
	List<AptInfo> selectByDongCode(long dongCode) throws SQLException;
	List<AptDeal> selectByAptCode(long aptCode);
}
