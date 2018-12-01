package com.itc.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;

import com.itc.models.DatabaseDetails;

/**
 * The Class DatabaseHandlerImpl.
 * @author Debadatta Mishra
 */
public class DatabaseHandlerImpl implements DatabaseHandler {

	/** The db details. */
	@Autowired
	private DatabaseDetails dbDetails;

	/* (non-Javadoc)
	 * @see com.itc.handlers.DatabaseHandler#getConnection()
	 */
	public Connection getConnection() {
		// Get Oracle Connection
		return getOracleConnection(dbDetails.getHostUrl(),
				dbDetails.getUserName(), dbDetails.getPassword(),
				dbDetails.getSchemaName());
	}

	/* (non-Javadoc)
	 * @see com.itc.handlers.DatabaseHandler#executeQuery(java.lang.String)
	 */
	public Object executeQuery(String query) {
		String result = null;
		Connection conn = getConnection();
		System.out.println("Connection----->" + conn);
		System.out.println("query String---->" + query);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getString(1);
				System.out.println("Result----->" + result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Gets the oracle connection.
	 *
	 * @param host the host
	 * @param userName the user name
	 * @param pwd the pwd
	 * @param schema the schema
	 * @return the oracle connection
	 */
	public Connection getOracleConnection(String host, String userName,
			String pwd, String schema) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String dbUrl = "jdbc:oracle:thin:@" + host + ":1521:" + schema;
			conn = DriverManager.getConnection(dbUrl, userName, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
