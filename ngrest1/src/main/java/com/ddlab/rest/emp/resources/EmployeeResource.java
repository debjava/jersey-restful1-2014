package com.ddlab.rest.emp.resources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.ddlab.rest.base.resources.BaseResource;
import com.ddlab.rest.entity.User;

@Path("1/empresource")//Vesrion/Resources
public class EmployeeResource extends BaseResource {

	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@Context
	HttpServletResponse response;

	@Path("/login")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createUser(User user) {
		//POST http://localhost:8080/ngrest1/empresources/1/empresource/login
		//Content-Type:application/json
		//Payload : {"username":"Deba","password":"deba","id":"123"}
		System.out.println("User :::"+user);
		ResponseBuilder responseBuilder = null;
		try {
			if(user.getUserName() == null || user.getPassword() == null )
			
				//Simply, you can omit also
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
				
				responseBuilder = Response.status(Status.BAD_REQUEST).entity("Incorrect password");
			responseBuilder = Response.status(Status.OK);
		} catch (Exception e) {
			responseBuilder = Response.serverError();
		}
		return responseBuilder.build();
	}

//	@Path("/update")
//	@PUT
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public void updateUser(User user) { //204 No content
//		//DELETE http://localhost:8080/jerseyrest2x/empresources/1/empresource/update
//		//Content-Type:application/json
//		//Payload : {"username":"Deba","password":"deba","id":"123"}
//		if(user.getUserName() == null || user.getPassword() == null )
//			throw createWebappException(new IllegalArgumentException("Incorrect credentials"));
//		empService.updateUser(user);
//	}
//	
//	@Path("/delete")
//	@DELETE
//	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public String deleteUser(User user) { 
//		//DELETE http://localhost:8080/jerseyrest2x/empresources/1/empresource/delete
//		//Content-Type:application/json
//		//Payload : {"username":"Deba","password":"deba","id":"123"}
//		if(user.getUserName() == null || user.getPassword() == null )
//			throw createWebappException(new IllegalArgumentException("Incorrect credentials"));
//		empService.deleteUser(user);
//		return "User "+user.getUserName()+" has been removed from the system successfully";
//	}
//	
//	@Path("/userid")
//	@GET
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public User getUser(@QueryParam("id") int id) { 
//		//GET http://localhost:8080/jerseyrest2x/empresources/1/empresource/userid?id=123
//		//Accept:application/json or application/xml
//		if(id == 0 )
//			throw createWebappException(new IllegalArgumentException("Incorrect ID"));
//		return empService.getUserById(String.valueOf(id));
//	}
//	
//	@Path("/show/image")
//	@GET
//	@Produces("image/png")
//	public Response showImage() {
//		//GET http://localhost:8080/jerseyrest2x/empresources/1/empresource/show/image
//		//You can directly hit in the browser
//		InputStream inStream = null;
//		try {
//			String SERVER_DOWNLOAD_LOCATION_FOLDER = "D:"+File.separator+"temp"+File.separator;
//			String IMG_FILE_NAME = "r.jpg";
//			inStream = new FileInputStream(SERVER_DOWNLOAD_LOCATION_FOLDER+IMG_FILE_NAME);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return Response.ok().entity(inStream).build();
//	}
}
