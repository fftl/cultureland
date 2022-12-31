package com.ssafy.culture.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.culture.dao.AptDao;
import com.ssafy.culture.dto.AptDeal;
import com.ssafy.culture.dto.AptInfo;

@Service
public class AptServiceImpl implements AptService{
	
	private AptDao aptDao;
	
	@Autowired
	public AptServiceImpl(AptDao aptDao) {
		super();
		this.aptDao = aptDao;
	}

	@Override
	public List<AptInfo> getAptInfoByDongCode(long dongCode) throws SQLException {
		return aptDao.selectByDongCode(dongCode);
	}

	@Override
	public List<AptDeal> getAptDealAptCode(long aptCode) throws SQLException {
		return aptDao.selectByAptCode(aptCode);
	}

}
