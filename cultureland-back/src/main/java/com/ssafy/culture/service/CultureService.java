package com.ssafy.culture.service;

import java.util.List;
import java.util.Map;

import com.ssafy.culture.dto.Culture;

public interface CultureService {
	int addCulture(Map<String, Object> list);
	List<Culture> getAllCulture();
}
