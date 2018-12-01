package com.ddlab.rest.service;

import com.ddlab.rest.entity.User;

public interface EmployeeService {
	
	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public User getUserById(String id);

}
