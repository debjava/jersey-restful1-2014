package com.itc.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;

import com.itc.models.Registration;

/**
 * The Class RegistrationHandler.
 * @author Debadatta Mishra
 */
public class RegistrationHandler {
	
	/** The handler. */
	@Autowired
	private DatabaseHandler handler;
	
	/**
	 * Save registration.
	 *
	 * @param registration the registration
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean saveRegistration(Registration registration) throws Exception {
		boolean saveFlag = false;
		try {
			Connection conn = handler.getConnection();
			String maxIdQuery = "select max(id) from ITCREGISTRATION";
			int id = executeIdQuery(conn, maxIdQuery);
			String insertQuery = "insert into ITCREGISTRATION values("+(++id)+","+"\'"+registration.getFirstName()+"\'"+","
					+"\'"+registration.getLastName()+"\'"+","
					+"\'"+registration.getUserName()+"\'"+","
					+"\'"+registration.getPassword()+"\'"+","
					+registration.getAge()+","
					+"\'"+registration.getEmail()+"\'"+","
					+"\'"+registration.getPhoneNumber()+"\'"+")";
			executeInsertQuery(conn, insertQuery);
		}
		catch(Exception e) {
			throw e;
		}
		return saveFlag;
	}
	
	/**
	 * Execute id query.
	 *
	 * @param conn the conn
	 * @param query the query
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	private int executeIdQuery(Connection conn,String query) throws SQLException {
		int id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	/**
	 * Execute insert query.
	 *
	 * @param conn the conn
	 * @param query the query
	 * @throws SQLException the SQL exception
	 */
	private void executeInsertQuery(Connection conn,String query) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
