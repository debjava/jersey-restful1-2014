package com.ddlab.rnd.server;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ddlab.rnd.beans.Employee;
import com.ddlab.rnd.util.Constants;
import com.ddlab.rnd.util.FileUtil;

@Path("ddlab/emp/1")
public class EmpCreateService {
	
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@Path("/createemp")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createEmp(Employee emp) {
		System.out.println("Employee object received successfully .........");
		try {
			long time = new Date().getTime();
			if( emp.getId() == 0 )
				emp.setId(time);
			FileUtil.saveFile(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+"-"+time+".json", emp.toJSON());
			return Response.ok("Employee created successfully...").entity(emp).build();
		}
		catch(RuntimeException re) {
			re.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(re.getMessage()).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
