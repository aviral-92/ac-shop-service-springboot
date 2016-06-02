package com.ac.pojo;

public class ColumnDetail {

	private int id;
	private String columnName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public ColumnDetail(int id, String columnName) {
		super();
		this.id = id;
		this.columnName = columnName;
	}

	public ColumnDetail() {
		// TODO Auto-generated constructor stub
	}
}
