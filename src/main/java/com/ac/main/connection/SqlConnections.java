package com.ac.main.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ac.pojo.UserDetail;
import com.ac.pojo.UserDetails;

@Repository
public class SqlConnections {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Alert alert;

	// @Value("{all.employee.show}
	boolean isAllEmployeeShow = true; // AS of now from hard code

	public boolean connections(String username, String password, String role) {

		System.out.println(jdbcTemplate);
		boolean response = false;
		try {
			if (con == null) {
				con = connection();
			}
			ps = con.prepareStatement("SELECT * FROM LOGIN WHERE USERNAME =? and PASSWORD = ? and ROLE = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, role);
			rs = ps.executeQuery();
			if (rs.next()) {

				System.out.println("Welcome You Successfully Logged in.");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText(username + ", you had logged in");
				alert.showAndWait();
				response = true;

			} else {

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Check your Credentials");
				alert.showAndWait();
				response = false;
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			response = false;
		}
		return response;
	}

	public String addUser(List<UserDetail> userDetailsList) {

		String response = "";
		if (con == null) {
			con = connection();
		}

		String sql = "INSERT INTO userdetail (userId,name,email,mobile,username,password) values (0,?,?,?,?,?)";
		try {
			// ps = con.prepareStatement(sql);

			if (userDetailsList != null && userDetailsList.size() > 0) {
				if (!isExist(userDetailsList.get(2).getValue(), userDetailsList
						.get(1).getValue(), userDetailsList.get(3).getValue())) {
					ps = con.prepareStatement(sql);
					if (userDetailsList.get(4).getValue()
							.equals(userDetailsList.get(5).getValue())) {
						userDetailsList.remove(5);
						int i = 1;
						for (UserDetail userDetail : userDetailsList) {
							if (!StringUtils.isEmpty(userDetail.getValue())) {
								ps.setString(i, userDetail.getValue());
								i++;

							} else {
								// Alert userDetailsList.get(i).getValue() is
								// Empty
								response = userDetail.getValue() + " is null";
								break;
							}
						}
						int stmtExecute = ps.executeUpdate();
						if (stmtExecute > 0) {
							response = "Successfully Added";
						}
					} else {
						// Alert username & password are not equal.
						response = "username & password are not equal";
					}
				} else {
					response = "Username already Exist";
				}
			} else {
				// Fields are Empty.
				response = "Fields are empty";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return response;
	}

	public List<UserDetails> getUser(List<UserDetail> userDetailsList) {

		if (con == null) {
			con = connection();
		}

		boolean isRecordExist = false;
		UserDetails userDetails = null;
		List<UserDetails> detailsList = new ArrayList<UserDetails>();
		try {
			String sql = "SELECT * FROM userdetail WHERE name = ? OR mobile = ? OR email = ? or username = ? ";
			ps = con.prepareStatement(sql);
			if (userDetailsList != null && userDetailsList.size() > 0) {

				ps.setString(1, userDetailsList.get(0).getValue());
				ps.setString(2, userDetailsList.get(1).getValue());
				ps.setString(3, userDetailsList.get(2).getValue());
				ps.setString(4, userDetailsList.get(3).getValue());
				rs = ps.executeQuery();
				if (rs.next()) {
					userDetails = new UserDetails(rs.getString("username"),
							rs.getString("name"), rs.getString("email"),
							rs.getString("mobile"));
					detailsList.add(userDetails);
					isRecordExist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isAllEmployeeShow) {
			if (!isRecordExist) {
				String sql = "SELECT * FROM userdetail ";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						userDetails = new UserDetails(rs.getString("username"),
								rs.getString("name"), rs.getString("email"),
								rs.getString("mobile"));
						detailsList.add(userDetails);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		return detailsList;
	}

	private boolean isExist(String mobile, String email, String username) {

		String query = "SELECT * FROM userdetail WHERE username = ? OR email = ? OR mobile = ? ";
		if (con == null) {
			con = connection();
		}
		boolean isExist = false;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, mobile);
			rs = ps.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			isExist = false;
		}
		return isExist;
	}

	private Connection connection() {

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://10.1.249.41:3306/ac_service", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
