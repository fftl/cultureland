package com.ssafy.culture.dto;

//사용자의 정보입니다.
public class User {
	private long userid;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String token;
	private String name;

	public User() {
		super();
	}

	public User(long userid, String username, String password, String nickname, String email, String token,String name) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.token = token;
		this.name = name;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", nickname="
				+ nickname + ", email=" + email + ", token=" + token + ", name=" + name + "]";
	}



}
