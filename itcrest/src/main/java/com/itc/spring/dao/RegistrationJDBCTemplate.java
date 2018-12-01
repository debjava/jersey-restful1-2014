package com.itc.spring.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.itc.models.Registration;

public class RegistrationJDBCTemplate implements RegistrationDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void createRegistration(Registration registration) {
		String maxIdQuery = "select max(id) from ITCREGISTRATION";
		int id = jdbcTemplateObject.queryForInt(maxIdQuery);
		System.out.println("Max ID in jdbc template ::::"+id);
		String insertQuery = "insert into ITCREGISTRATION values("+(++id)+","+"\'"+registration.getFirstName()+"\'"+","
				+"\'"+registration.getLastName()+"\'"+","
				+"\'"+registration.getUserName()+"\'"+","
				+"\'"+registration.getPassword()+"\'"+","
				+registration.getAge()+","
				+"\'"+registration.getEmail()+"\'"+","
				+"\'"+registration.getPhoneNumber()+"\'"+")";
		jdbcTemplateObject.update(insertQuery);
	}

}
