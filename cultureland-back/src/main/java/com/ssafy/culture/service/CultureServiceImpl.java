package com.ssafy.culture.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.culture.dao.CultureDao;
import com.ssafy.culture.dto.Culture;

@Service
public class CultureServiceImpl implements CultureService {
	
	private CultureDao cultureDao;
	
	@Autowired
	public CultureServiceImpl(com.ssafy.culture.dao.CultureDao cultureDao) {
		super();
		this.cultureDao = cultureDao;
	}

	@Override
	public int addCulture(Map<String, Object> map) {
		return cultureDao.insertCulture(map);
	}

	@Override
	public List<Culture> getAllCulture() {
		return cultureDao.selectAllCulture();
	}

}
