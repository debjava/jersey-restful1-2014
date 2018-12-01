package com.itc.resource.services;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.itc.handlers.DatabaseHandler;
import com.itc.models.User;
import com.itc.spring.dao.LoginDAO;

/**
 * The Class LoginService.
 * @author Debadatta Mishra
 */
@Path("1/loginservice")
public class LoginService {
	
	/** The request. */
	@Context
	HttpServletRequest request;

	/** The servlet config. */
	@Context
	ServletConfig servletConfig;
	
	/** The db handler. */
	@Autowired
	private DatabaseHandler dbHandler;
	
	@Autowired
	private LoginDAO loginDAO;
	
	/**
	 * Login user.
	 *
	 * @param user the user
	 * @return the response
	 */
	@Path("/login")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response loginUser(User user) {
		ResponseBuilder responseBuilder = null;
		try {
			/*
			 * Testing
			 */
			System.out.println("--------------------------------------------");
			String userPwdFromDb = loginDAO.getPassword(user);
			System.out.println("user password from DB ::::"+userPwdFromDb);
			//~~~~~~~~~~~~~~~~~~~~~~~~
			String query = "select password from itcusers where username = "+"\'"+user.getUserName()+"\'";
			String pwdFromDb = (String)dbHandler.executeQuery(query);
			if( pwdFromDb == null ) {
//				System.out.println("User does not exist...");
				responseBuilder = Response.status(Status.BAD_REQUEST).entity("User does not exist in the system, Please register in the system");
			}
			else if(pwdFromDb.equals(user.getPassword())) {
				System.out.println("User exists, both username and password match");
				responseBuilder = Response.ok("Login successfull");
			}
			else 
				responseBuilder = Response.status(Status.BAD_REQUEST).entity("Incorrect password");
		} catch (Exception e) {
			responseBuilder = Response.serverError();
		}
		return responseBuilder.build();
	}
}
