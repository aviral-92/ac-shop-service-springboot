package com.ac.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ac.pojo.LoginDetails;

public class LoginExtractor implements ResultSetExtractor<LoginDetails> {

	@Override
	public LoginDetails extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		LoginDetails loginDetails = new LoginDetails();
		if (rs.next()) {
			loginDetails.setUsername(rs.getString("username"));
			loginDetails.setPassword(rs.getString("password"));
			loginDetails.setRole(rs.getString("role"));
		}
		return loginDetails;
	}

}
