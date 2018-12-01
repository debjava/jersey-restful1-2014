package com.itc.restful.jersey.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.itc.restful.jersey.jaxb.Employee;
import com.itc.restful.util.Constants;
import com.itc.restful.util.FileUtil;

/**
 * @author Debadatta Mishra
 *
 */
@Path("itc")
public class ITCInfotechServices {

	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getOrganisationName() {
		return "ITC Infotech, Bangalore, Karnataka";
	}

	@Path("/address")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAddress() {
		// URL : http://localhost:8080/itcrestful/itc/address
		return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @PathParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/address/{areaCode}")
	// USA,EUROPE,ASIA
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAddressByCode(@PathParam("areaCode") String areaCode) {
		// http://localhost:8080/itcrestful/itc/address/USA
		// http://localhost:8080/itcrestful/itc/address/europe
		try {
			if (areaCode.equalsIgnoreCase("USA"))
				return "12 North State, Route 17,Suite 303,Paramus,New Jersey,NJ-07652";
			else if (areaCode.equalsIgnoreCase("Europe"))
				return "Newell Consulting Oy,P.O. Box 16 , Olari,02211 Espoo, Helsinki";
			else if (areaCode.equalsIgnoreCase("Africa"))
				return "Johannesburg,2nd Floor, West Tower,Nelson Mandela Square,Maude Street, Sandton,Johannesburg, 2196";
			else if (areaCode.equalsIgnoreCase("Asia"))
				return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
			else {
				return "No such area code exists for ITC";
			}
				
		} catch (Exception e) {
			return "No such area code exists for ITC";
		}
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @QueryParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/regionaladdress/{areaCode}")
	// USA,EUROPE,ASIA
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAddressByCountry(@PathParam("areaCode") String areaCode,
			@QueryParam("country") String country) {
		// http://localhost:8080/itcrestful/itc/regionaladdress/Europe?country=FI
		try {
			if (areaCode.equalsIgnoreCase("USA")
					&& country.equalsIgnoreCase("NJ"))
				return "12 North State, Route 17,Suite 303,Paramus,New Jersey,NJ-07652";
			else if (areaCode.equalsIgnoreCase("Europe")
					&& country.equalsIgnoreCase("FI"))
				return "Newell Consulting Oy,P.O. Box 16 , Olari,02211 Espoo, Helsinki";
			else if (areaCode.equalsIgnoreCase("Europe")
					&& country.equalsIgnoreCase("SE"))
				return "C/o Matrisen AB,Box 22059 , 104 22 Stockholm";
			else if (areaCode.equalsIgnoreCase("Europe")
					&& country.equalsIgnoreCase("DK"))
				return "Havnegade 39, 3. sal,1058 Copenhagen K";
			else if (areaCode.equalsIgnoreCase("Asia")
					&& country.equalsIgnoreCase("IN"))
				return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
			else
				return "No such area code exists for ITC";
		} catch (Exception e) {
			return "No such area code exists for ITC";
		}
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @MatrixParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// /books/2011;author=mkyong;country=malaysia”

	@Path("/itcaddress")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getITCAddress(@MatrixParam("country") String country,
			@MatrixParam("areacode") String areaCode) {
		// http://localhost:8080/itcrestful/itc/itcaddress;country=FI;areacode=europe
		return getAddressByCountry(areaCode, country);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @FormParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/postaddress")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postNGetITCAddress(@FormParam("country") String country,
			@FormParam("areacode") String areaCode) {
		// http://localhost:8080/itcrestful/itc/postaddress
		return getAddressByCountry(areaCode, country);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @PathParam with Object ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/emp/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getEmployee(@PathParam("id") String id) {
//		Address adrs = new Address();
//		adrs.setCity("Bangalore");
//		adrs.setCountry("Karnataka");
//		adrs.setDoorNo("12-A");
//		adrs.setStreetId("Street-11");
//
//		Employee emp = new Employee();
//		emp.setAdrs(adrs);
//		emp.setAge(23);
//		emp.setEmailId("deba@gmail.com");
//		emp.setName("Deba");
		ObjectMapper mapper = new ObjectMapper();
		Employee emp = null;
		try {
			File file = new File(Constants.DATA_LOCATION_FOLDER+File.separator+id+".json");
			if( ! file.exists() )
				return Response.status(404).entity("No information found for the given employee id ...").build();
			emp = mapper.readValue(file, Employee.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return emp;
		return Response.ok(emp).build();
	}

	@Path("/createemp")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createEmp(Employee emp) {
		System.out.println("Employee object received successfully .........");
		//store into file system
//		if( new File(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+".json").exists()) {
//			return Response.status(409).entity("Employee already exists in the system ...").build();
//		}
//		FileUtil.saveFile(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+".json", emp.toJSON());
//		return Response.ok("Employee created successfully...").build();
		
		
		
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		FileUtil.saveFile(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+new Date().getTime()+".json", emp.toJSON());
		return Response.ok("Employee created successfully...").build();
		
		
		
	}
	
	@Path("/updateemp")
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateEmp(Employee emp) {
		System.out.println("Employee object received successfully .........");
		//store into file system
		if( new File(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+".json").exists()) {
			FileUtil.saveFile(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+".json", emp.toJSON());
			return Response.ok("Employee info updated successfully...").build();
			
		}
		else {
			return Response.status(404).entity("Employee information not found, hence can not be updated ...").build();
		}	
	}
	
	@Path("/deleteemp")
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deleteEmp(Employee emp) {
		System.out.println("Employee object received successfully .........");
		//store into file system
		File file = new File(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+".json");
		if( file.exists()) {
			file.delete();
			return Response.ok("Employee info deleted successfully...").build();
			
		}
		else
			return Response.status(404).entity("Employee information not found, hence can not be deleted ...").build();
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form) {
		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER
				+ headerOfFilePart.getFileName();
		// save the file to the server
		saveFile(fileInputStream, filePath);
		String output = "File saved to server location using FormDataMultiPart : "
				+ filePath;
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(output).build();
	}

	@Path("/download")
	@GET
//	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Produces("image/jpg")
	public Response getFile() {

		byte[] docStream = readContents("D:/temp/d.doc");

		return Response.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename = r.jpg")
				.build();
	}

	private byte[] readContents(String filePath) {

		File file = new File(filePath);
		byte[] buffer = new byte[(int) file.length()];
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(file);
			inStream.read(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null)
					inStream.close();
			} catch (Exception e2) {
			}
		}

		return buffer;

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
