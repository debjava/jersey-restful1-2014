package com.ddlab.rnd.dao;
// Abstract class DAO Factory
public abstract class DAOFactory {

	public abstract CustomerDAO getCustomerDAO();
	
	public static DAOFactory getDAOFactory(DatabaseType dbType ) {
		switch(dbType) {
		case ORACLE : 
			return new OracleDAOFactory();
		case MYSQL :
			return new MySQLDAOFactory();
		default : 
			return null;
		}
	}
}