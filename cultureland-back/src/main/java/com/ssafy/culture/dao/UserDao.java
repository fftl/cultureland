package com.ssafy.culture.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.culture.dto.User;

@Mapper
public interface UserDao {

	int insertUser(User user) throws SQLException;

	User selectUser(User user) throws SQLException;

	User userInfo(long userid) throws SQLException;

	int updateUser(User user) throws SQLException;
	
	int updateUserPassword(User user) throws SQLException; 

	int deleteUser(long userid) throws SQLException;

	String selectId(Map<String,String> userinfo) throws SQLException;
	
	User selectPassword(Map<String, String> username) throws SQLException;

	public void saveRefreshToken(Map<String, String> map) throws SQLException;

	public Object getRefreshToken(String userid) throws SQLException;

	public void deleteRefreshToken(Map<String, String> map) throws SQLException;

	User selectUserName(String username) throws SQLException;

	User selectUserEmail(String email) throws SQLException;
}
