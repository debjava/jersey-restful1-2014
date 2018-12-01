package com.itc.restful.jersey.server;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.itc.restful.jersey.jaxb.Address;
import com.itc.restful.jersey.jaxb.Employee;

@Path("itc")
public class ITCInfotech {

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
		//URL : http://localhost:8080/itcrestful/itc/address
		return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
	}

	//~~~~~~~~~~~~~~~~~~~~~~~ @PathParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/address/{areaCode}") //USA,EUROPE,ASIA
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAddressByCode( @PathParam("areaCode") String areaCode) {
		// http://localhost:8080/itcrestful/itc/address/USA
		// http://localhost:8080/itcrestful/itc/address/europe
		try {
			if( areaCode.equalsIgnoreCase("USA"))
				return "12 North State, Route 17,Suite 303,Paramus,New Jersey,NJ-07652";
			else if( areaCode.equalsIgnoreCase("Europe"))
				return "Newell Consulting Oy,P.O. Box 16 , Olari,02211 Espoo, Helsinki";
			else if( areaCode.equalsIgnoreCase("Africa"))
				return "Johannesburg,2nd Floor, West Tower,Nelson Mandela Square,Maude Street, Sandton,Johannesburg, 2196";
			else if( areaCode.equalsIgnoreCase("Asia"))
				return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
			else 
				return "No such area code exists for ITC";
		}
		catch(Exception e) {
			return "No such area code exists for ITC";
		}
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~ @QueryParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Path("/regionaladdress/{areaCode}") //USA,EUROPE,ASIA
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAddressByCountry( @PathParam("areaCode") String areaCode , @QueryParam("country") String country ) {
		//http://localhost:8080/itcrestful/itc/regionaladdress/Europe?country=FI
		try {
			if( areaCode.equalsIgnoreCase("USA") && country.equalsIgnoreCase("NJ"))
				return "12 North State, Route 17,Suite 303,Paramus,New Jersey,NJ-07652";
			else if( areaCode.equalsIgnoreCase("Europe") && country.equalsIgnoreCase("FI"))
				return "Newell Consulting Oy,P.O. Box 16 , Olari,02211 Espoo, Helsinki";
			else if( areaCode.equalsIgnoreCase("Europe") && country.equalsIgnoreCase("SE"))
				return "C/o Matrisen AB,Box 22059 , 104 22 Stockholm";
			else if( areaCode.equalsIgnoreCase("Europe") && country.equalsIgnoreCase("DK"))
				return "Havnegade 39, 3. sal,1058 Copenhagen K";
			else if( areaCode.equalsIgnoreCase("Asia") && country.equalsIgnoreCase("IN") )
				return "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
			else 
				return "No such area code exists for ITC";
		} catch (Exception e) {
			return "No such area code exists for ITC";
		}
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~ @MatrixParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// /books/2011;author=mkyong;country=malaysia”
	
	@Path("/itcaddress")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getITCAddress( @MatrixParam("country") String country , @MatrixParam("areacode") String areaCode ) {
		//http://localhost:8080/itcrestful/itc/itcaddress;country=FI;areacode=europe
		return getAddressByCountry(areaCode, country);
	}
	
	@Path("/postaddress")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postNGetITCAddress( @FormParam("country") String country , @FormParam("areacode") String areaCode ) {
		//http://localhost:8080/itcrestful/itc/postaddress
		/*
		 * click on x-www-form-urlencoded
		 * country - FI
		 * areacode - europe
		 */
		return getAddressByCountry(areaCode, country);
		
		/*
		 * return Response.status(200)
			.entity("addUser is called, name : " + name + ", age : " + age)
			.build();
		 */
	}
	
	@Path("/emp/{id}")
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//	public Employee getEmployee( @PathParam("id") String id ) {
	public Response getEmployee( @PathParam("id") String id ) {
		Address adrs = new Address();
		adrs.setCity("Bangalore");
		adrs.setCountry("Karnataka");
		adrs.setDoorNo("12-A");
		adrs.setStreetId("Street-11");
		
		Employee emp = new Employee();
		emp.setAdrs(adrs);
		emp.setAge(23);
		emp.setEmailId("deba@gmail.com");
		emp.setName("Deba");
		
//		return emp;
		
		return Response.ok(emp).build();
		
//		Response.ok().
	}
	
	@Path("/createemp")
	@POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response createEmp( Employee emp ) {
		System.out.println("Employee object received successfully .........");
		return Response.ok(emp).build();
	}

}
