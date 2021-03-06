package com.ddlab.rest.itc.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.ddlab.rest.entity.Constants;
import com.ddlab.rest.entity.Employee;
import com.ddlab.rest.entity.FileUtil;

@Path("itc")
public class ITCInfotechServices {

	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getOrganisationName() {
		//GET http://localhost:8080/jerseyrest2x/itcresources/itc
		return "ITC Infotech, Bangalore, Karnataka";
	}

	@Path("/address")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAddress() {
		// URL : http://localhost:8080/jerseyrest2x/itcresources/itc/address
		return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @PathParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/address/{areaCode}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAddressByCode(@PathParam("areaCode") String areaCode) {
		//		GET http://localhost:8080/jerseyrest2x/itcresources/itc/address/USA
		try {
			if (areaCode.equalsIgnoreCase("USA"))
				return "12 North State, Route 17,Suite 303,Paramus,New Jersey,NJ-07652";
			else if (areaCode.equalsIgnoreCase("Europe"))
				return "Newell Consulting Oy,P.O. Box 16 , Olari,02211 Espoo, Helsinki";
			else if (areaCode.equalsIgnoreCase("Africa"))
				return "Johannesburg,2nd Floor, West Tower,Nelson Mandela Square,Maude Street, Sandton,Johannesburg, 2196";
			else if (areaCode.equalsIgnoreCase("Asia"))
				return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
			else
				return "No such area code exists for ITC";
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
		// GET http://localhost:8080/jerseyrest2x/itcresources/itc/regionaladdress/Europe?country=FI
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


	@Path("/itcaddress")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getITCAddress(@MatrixParam("country") String country,
			@MatrixParam("areacode") String areaCode) {
		// GET http://localhost:8080/jerseyrest2x/itcresources/itc/itcaddress;country=FI;areacode=europe
		return getAddressByCountry(areaCode, country);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @FormParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/postaddress")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postNGetITCAddress(@FormParam("country") String country,
			@FormParam("areacode") String areaCode) {
		// http://localhost:8080/jerseyrest2x/itcresources/itc/postaddress
		return getAddressByCountry(areaCode, country);
	}

	/*
	 * In case of Firefox Rest Client
		Set in the header
		�name� = �Content-Type� and �value� = �application/x-www-form-urlencoded�
		Then set the header as 
		country - FI
		areacode - europe

		In case of Chrome Postman
		Click on x-www-form-urlencoded in Postman
		country - FI
		areacode - europe
	 */

	// ~~~~~~~~~~~~~~~~~~~~~~~ @PathParam with Object ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/emp/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getEmployee(@PathParam("id") String id) {
		//GET http://localhost:8080/jerseyrest2x/itcresources/itc/emp/123
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
		return Response.ok(emp).build();
	}

	/**
	 * POST http://localhost:8080/jerseyrest2x/itcresources/itc/createemp
	 * 
	 * JSON Structure
	 * {
		    "Name": "Deba",
		    "Age": 23,
		    "Email": "deba@gmail.com",
		    "Adrs": {
		        "DoorNo": "12-A",
		        "Street": "Street-11",
		        "City": "Bangalore",
		        "Country": "Karnataka"
		    }
		}
		
		XML Structure
			<Emp>
			    <Name>Deba</Name>
			    <Age>23</Age>
			    <Email>deba@gmail.com</Email>
			    <Adrs>
			        <DoorNo>12-A</DoorNo>
			        <Street>Street-11</Street>
			        <City>Bangalore</City>
			        <Country>Karnataka</Country>
			    </Adrs>
		   </Emp>
	 */
	@Path("/createemp")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createEmp(Employee emp) {
		System.out.println("Employee object received successfully .........");
		//store into file system
		if( new File(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+".json").exists()) {
			return Response.status(409).entity("Employee already exists in the system ...").build();
		}
		FileUtil.saveFile(Constants.DATA_LOCATION_FOLDER+File.separator+emp.getName()+".json", emp.toJSON());
		return Response.ok("Employee created successfully...").build();
	}

	/**
	 * PUT http://localhost:8080/jerseyrest2x/itcresources/itc/updateemp
	 * 
	 * JSON Structure
	 * {
		    "Name": "Deba",
		    "Age": 23,
		    "Email": "deba@gmail.com",
		    "Adrs": {
		        "DoorNo": "12-A",
		        "Street": "Street-11",
		        "City": "Bangalore",
		        "Country": "Karnataka"
		    }
		}
		
		XML Structure
			<Emp>
			    <Name>Deba</Name>
			    <Age>23</Age>
			    <Email>deba@gmail.com</Email>
			    <Adrs>
			        <DoorNo>12-A</DoorNo>
			        <Street>Street-11</Street>
			        <City>Bangalore</City>
			        <Country>Karnataka</Country>
			    </Adrs>
		   </Emp>
	 */
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
		else
			return Response.status(404).entity("Employee information not found, hence can not be updated ...").build();
	}

	/**
	 * PUT http://localhost:8080/jerseyrest2x/itcresources/itc/deleteemp
	 * 
	 * JSON Structure
	 * {
		    "Name": "Deba",
		    "Age": 23,
		    "Email": "deba@gmail.com",
		    "Adrs": {
		        "DoorNo": "12-A",
		        "Street": "Street-11",
		        "City": "Bangalore",
		        "Country": "Karnataka"
		    }
		}
		
		XML Structure
			<Emp>
			    <Name>Deba</Name>
			    <Age>23</Age>
			    <Email>deba@gmail.com</Email>
			    <Adrs>
			        <DoorNo>12-A</DoorNo>
			        <Street>Street-11</Street>
			        <City>Bangalore</City>
			        <Country>Karnataka</Country>
			    </Adrs>
		   </Emp>
	 */
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
		//POST http://localhost:8080/jerseyrest2x/itcresources/itc/upload
		//Content-Type:multipart/form-data
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

	@POST
	@Path("/upload1")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		//POST http://localhost:8080/jerseyrest2x/itcresources/itc/upload1
		//Content-Type:multipart/form-data
		String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
		// save the file to the server
		String output = ""; 
		try {
			saveFile(fileInputStream, filePath);
			output = "File saved to server location : " + filePath+" successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Unexpected server exception while uploading the file.";
		}
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/upload3")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadFileWithData1(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition cdh,
			@FormDataParam("emp") String emp) throws Exception { 
		//http://localhost:8080/jerseyrest2x/itcresources/itc/upload3
		//Content-Type:multipart/form-data
		//form data as emp:abcd
		try {
			System.out.println("Emp data as String :::"+emp);
			//System.out.println("--->"+URLDecoder.decode(emp,"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String output = ""; 
		try {
			String filePath = Constants.SERVER_UPLOAD_LOCATION_FOLDER + cdh.getFileName();
			saveFile(fileInputStream, filePath);
			output = "File saved to server location : " + filePath+" successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Unexpected server exception while uploading the file.";
		}
		return Response.status(200).entity(output).build();
	} 

	@Path("/download")
	@GET
	//	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Produces("image/jpg")
	public Response getFile() {
		//GET http://localhost:8080/jerseyrest2x/itcresources/itc/download
		//You can invoke this url directly in the browser
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
