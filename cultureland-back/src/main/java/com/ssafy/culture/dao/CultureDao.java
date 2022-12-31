package com.ssafy.culture.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.culture.dto.Culture;

@Mapper
public interface CultureDao {
	int insertCulture(Map<String, Object> map);
	List<Culture> selectAllCulture();
}
