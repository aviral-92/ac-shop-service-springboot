package com.ac.db;

import java.util.List;

import com.ac.pojo.UserDetail;
import com.ac.pojo.UserDetails;

public interface DbOperation {

	public boolean loginCheck(String username, String password, String role);

	public String addUser(List<UserDetail> userDetailsList);

	public List<UserDetails> getUser(List<UserDetail> userDetailsList);

	public boolean isExist(String mobile, String email, String username);
}
