package com.ddlab.rnd.dao.oracle;

import com.ddlab.rnd.beans.Customer;
import com.ddlab.rnd.dao.CustomerDAO;
import com.ddlab.rnd.dao.OracleDAOFactory;

public class OracleCustomerDAO implements CustomerDAO {

	@Override
	public void createCustomer() {
		System.out.println("Creating a customer");
	}

	@Override
	public Customer getCustomer(String id) {
		System.out.println("Get the customer from the db query");
		return null;
	}

	@Override
	public boolean updateCustomer(String id) {
		System.out.println("Updating the customer");
		return false;
	}

	@Override
	public void deleteCustomer(String id) {
		System.out.println("deleting the customer");
	}
}
