package com.ddlab.rnd.dao;

import java.sql.Connection;

import com.ddlab.rnd.dao.oracle.OracleCustomerDAO;

public class OracleDAOFactory extends DAOFactory {

	public static final String DRIVER= "<driver details>";
	public static final String DBURL= "database url";

	// method to create Cloudscape connections
	public static Connection createConnection() {
		return null;
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return new OracleCustomerDAO();
	}

}
