package com.ac.db.impl;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ac.db.DbOperation;
import com.ac.db.extractor.LoginExtractor;
import com.ac.db.extractor.UserExtractor;
import com.ac.pojo.LoginDetails;
import com.ac.pojo.UserDetail;
import com.ac.pojo.UserDetails;

@Repository
public class DbOperationImpl implements DbOperation {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Alert alert;

	@Override
	public boolean loginCheck(String username, String password, String role) {

		boolean success = false;
		String sql = "SELECT * FROM LOGIN WHERE USERNAME =? and PASSWORD = ? and ROLE = ? ";
		List<String> args = new ArrayList<String>();
		args.add(username);
		args.add(password);
		args.add(role);
		LoginDetails logindetails = jdbcTemplate.query(sql,
				new LoginExtractor(), args.toArray());
		if (logindetails != null) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText(username + ", you had logged in");
			alert.showAndWait();
			success = true;
		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Check your Credentials");
			alert.showAndWait();
			success = false;
		}
		return success;
	}

	@Override
	public String addUser(List<UserDetail> userDetailsList) {

		String sql = "INSERT INTO userdetail (userId,name,email,mobile,username) values (0,?,?,?,?)";
		List<String> args = new ArrayList<String>();
		if (userDetailsList != null && userDetailsList.size() > 0) {
			if (!isExist(userDetailsList.get(2).getValue(), userDetailsList
					.get(1).getValue(), userDetailsList.get(3).getValue())) {
				
				for (UserDetail userDetail : userDetailsList) {
					if (!StringUtils.isEmpty(userDetail.getValue())) {
						args.add(userDetail.getValue());
					}
				}
				int success = jdbcTemplate.update(sql, args.toArray());

			}
		}
		return null;
	}

	@Override
	public List<UserDetails> getUser(List<UserDetail> userDetailsList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExist(String mobile, String email, String username) {

		boolean success = false;
		String sql = "SELECT * FROM userdetail WHERE username = ? OR email = ? OR mobile = ? ";
		List<String> args = new ArrayList<String>();
		args.add(username);
		args.add(email);
		args.add(mobile);
		List<UserDetails> listUserDetail = jdbcTemplate.query(sql,
				new UserExtractor(), args.toArray());
		if (listUserDetail != null && listUserDetail.size() > 0) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exist");
			alert.setHeaderText(username + ", User Already Exist");
			alert.showAndWait();
			success = true;
		}

		return success;
	}
}
