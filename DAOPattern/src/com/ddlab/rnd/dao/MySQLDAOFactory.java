package com.ddlab.rnd.dao;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

	public static final String DRIVER=
			"COM.cloudscape.core.RmiJdbcDriver";
	public static final String DBURL=
			"jdbc:cloudscape:rmi://localhost:1099/CoreJ2EEDB";

	// method to create Cloudscape connections
	public static Connection createConnection() {
		return null;
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return null;
	}

}
