package com.ddlab.rest.service;

import com.ddlab.rest.entity.User;

public class LoginServiceImpl implements LoginService {

	public void createUser(User user) {
		System.out.println("User created successfully...");
	}

	public void updateUser(User user) {
		System.out.println("User information updated successfully...");
	}

	public void deleteUser(User user) {
		System.out.println("User information deleted successfully...");
	}

	public User getUserById(String id) {
		if( id == null ) throw new NullPointerException("User id can not be blank or empty");
		User user = new User();
		user.setPassword("");
		user.setUserName("Deb");
		user.setId(Integer.parseInt(id));
		return user;
	}

}
