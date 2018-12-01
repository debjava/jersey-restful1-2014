package com.ddlab.rnd.dao;

import com.ddlab.rnd.beans.Customer;

public interface CustomerDAO {
	
	public void createCustomer();
	public Customer getCustomer(String id);
	public boolean updateCustomer(String id);
	public void deleteCustomer(String id);
	
}
