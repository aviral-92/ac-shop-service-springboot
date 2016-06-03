package com.ac.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ac.pojo.UserDetails;

public class UserExtractor implements ResultSetExtractor<List<UserDetails>> {

	@Override
	public List<UserDetails> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		UserDetails userDetails;
		List<UserDetails> detailList = new ArrayList<UserDetails>();
		while (rs.next()) {
			userDetails = new UserDetails();
			userDetails.setUserId(rs.getInt("userId"));
			userDetails.setUsername(rs.getString("username"));
			userDetails.setName(rs.getString("name"));
			userDetails.setEmail(rs.getString("email"));
			userDetails.setMobile(rs.getString("mobile"));
			detailList.add(userDetails);
		}

		return detailList;
	}

}
