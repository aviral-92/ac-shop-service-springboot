package com.ac.pojo;

public class UserDetails {

	private int userId;
	private String username;
	private String name;
	private String email;
	private String mobile;

	public UserDetails(String username, String name, String email, String mobile) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
