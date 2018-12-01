package com.itc.models;

/**
 * The Class DatabaseDetails.
 * @author Debadatta Mishra
 */
public class DatabaseDetails {
	
	/** The db name. */
	private String dbName;
	
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The host url. */
	private String hostUrl;
	
	/** The schema name. */
	private String schemaName;
	
	/**
	 * Instantiates a new database details.
	 *
	 * @param dbName the db name
	 * @param userName the user name
	 * @param password the password
	 * @param hostUrl the host url
	 * @param schemaName the schema name
	 */
	public DatabaseDetails(String dbName, String userName, String password,
			String hostUrl, String schemaName) {
		super();
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
		this.hostUrl = hostUrl;
		this.schemaName = schemaName;
	}

	/**
	 * Gets the db name.
	 *
	 * @return the db name
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the host url.
	 *
	 * @return the host url
	 */
	public String getHostUrl() {
		return hostUrl;
	}

	/**
	 * Gets the schema name.
	 *
	 * @return the schema name
	 */
	public String getSchemaName() {
		return schemaName;
	}

}
