package com.ssafy.culture.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.culture.dto.User;

public interface UserService {

	public int addUser(User user) throws SQLException;

	public User getUser(long userId) throws SQLException;

	public int modifyUser(User user) throws SQLException;
	
	public int modifyUserPassword(User user) throws SQLException;

	public int removeUser(long userId) throws SQLException;

	public String findId(Map<String,String> userinfo) throws SQLException;
	
	public User findPassword(Map<String, String> request) throws SQLException;

	public User login(User user) throws SQLException;
	

	public void saveRefreshToken(long userId, String refreshToken) throws SQLException;

	public Object getRefreshToken(long userId) throws SQLException;

	public void deleRefreshToken(String userId) throws SQLException;

	public User getByUserName(String username) throws SQLException;
	
	public void sendEmail(User user) throws SQLException;
}
