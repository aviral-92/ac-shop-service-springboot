package com.ac.pojo;

public class TempUserDetail {
	private String username;
	private String name1;
	private String email;
	private String mobile;

	public TempUserDetail(String username, String name1, String email,
			String mobile) {
		super();
		this.username = username;
		this.name1 = name1;
		this.email = email;
		this.mobile = mobile;
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

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

}
