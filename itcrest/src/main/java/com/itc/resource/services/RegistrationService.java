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

import com.itc.exceptions.ValidationException;
import com.itc.handlers.DatabaseHandler;
import com.itc.handlers.RegistrationHandler;
import com.itc.handlers.Validator;
import com.itc.models.Registration;
import com.itc.spring.dao.RegistrationDAO;

/**
 * The Class RegistrationService.
 * @author Debadatta Mishra
 */
@Path("1/registrationservice")
public class RegistrationService {
	
	/** The request. */
	@Context
	HttpServletRequest request;

	/** The servlet config. */
	@Context
	ServletConfig servletConfig;
	
	/** The db handler. */
	@Autowired
	private DatabaseHandler dbHandler;
	
	/** The validator. */
	@Autowired
	private Validator validator;
	
	/** The reg handler. */
	@Autowired
	private RegistrationHandler regHandler;
	
	@Autowired
	private RegistrationDAO regDAO;
	
	/**
	 * Login user.
	 *
	 * @param registration the registration
	 * @return the response
	 */
	@Path("/register")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response registerUser(Registration registration) {
		ResponseBuilder responseBuilder = null;
		boolean validationFlag = false;
		try {
			validationFlag = validator.validate(registration);
			if(validationFlag) {
				/*
				 * Store the details into database
				 */
				System.out.println("All validations passed ....");
				System.out.println("regDAO--------->"+regDAO);
				regDAO.createRegistration(registration);
				responseBuilder = Response.ok("Registration successfull");
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			responseBuilder = Response.status(Status.BAD_REQUEST).entity(e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			responseBuilder = Response.serverError();
		}
		return responseBuilder.build();
	}

}
