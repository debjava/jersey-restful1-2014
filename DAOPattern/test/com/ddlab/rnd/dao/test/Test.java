package com.ddlab.rnd.dao.test;

import com.ddlab.rnd.dao.CustomerDAO;
import com.ddlab.rnd.dao.DAOFactory;
import com.ddlab.rnd.dao.DatabaseType;

public class Test {

	public static void main(String[] args) {
		CustomerDAO custDAO = DAOFactory.getDAOFactory(DatabaseType.ORACLE).getCustomerDAO();
		custDAO.createCustomer();
		custDAO.deleteCustomer("11");
	}

}
