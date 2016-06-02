package com.ac.pojo;

public class UserDetail {
	int id;
	String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public UserDetail(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
