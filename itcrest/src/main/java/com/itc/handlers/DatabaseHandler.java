package com.itc.handlers;

import java.sql.Connection;

/**
 * The Interface DatabaseHandler.
 * @author Debadatta Mishra
 */
public interface DatabaseHandler {
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection();
	
	/**
	 * Execute query.
	 *
	 * @param query the query
	 * @return the object
	 */
	public Object executeQuery(String query);

}
