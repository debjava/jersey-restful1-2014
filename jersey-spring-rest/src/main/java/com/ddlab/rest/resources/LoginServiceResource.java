package com.ddlab.rest.resources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.ddlab.rest.entity.User;
import com.ddlab.rest.service.LoginService;

@Path("1/loginservice")
public class LoginServiceResource extends BaseResource {

	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;

	@Autowired
	private LoginService loginService;

	@Path("/create")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createUser(User user) {
		ResponseBuilder responseBuilder = null;
		try {
			if(user.getUserName() == null || user.getPassword() == null )
				responseBuilder = Response.status(Status.BAD_REQUEST).entity("Incorrect password");
			loginService.createUser(user);
			responseBuilder = Response.status(Status.CREATED).entity("User created successfully");
		} catch (Exception e) {
			responseBuilder = Response.serverError();
		}
		return responseBuilder.build();
	}

	@Path("/update")
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void updateUser(User user) { //204 No content
		if(user.getUserName() == null || user.getPassword() == null )
			throw createWebappException(new IllegalArgumentException("Incorrect credentials"));
		loginService.updateUser(user);
	}
	
	@Path("/delete")
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String deleteUser(User user) { //204 No content
		if(user.getUserName() == null || user.getPassword() == null )
			throw createWebappException(new IllegalArgumentException("Incorrect credentials"));
		loginService.deleteUser(user);
		return "User "+user.getUserName()+" has been removed from the system successfully";
	}
	
	@Path("/userid")
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUser(@QueryParam("id") int id) { //204 No content
		if(id == 0 )
			throw createWebappException(new IllegalArgumentException("Incorrect ID"));
		return loginService.getUserById(String.valueOf(id));
	}
}
