package com.itc.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.itc.models.User;

public class LoginJDBCTemplate implements LoginDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public String getPassword(User user) {
		String query = "select password from itcusers where username = "+"\'"+user.getUserName()+"\'";
		List<String> pwdsFromDB = null;
		String userPwd = null;
		try {
			pwdsFromDB = (List<String>) jdbcTemplateObject.queryForList(query, String.class);
			userPwd = (pwdsFromDB.size() != 0) ? pwdsFromDB.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userPwd;
	}

}
