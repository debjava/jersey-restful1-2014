package com.itc.restful.jersey.entity;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonParser;

@XmlRootElement(name = "Emp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

	@XmlElement(name = "Name")
	@JsonProperty("Name")
	private String name;

	@XmlElement(name = "Age")
	@JsonProperty("Age")
	private int age;

	@XmlElement(name = "Email")
	@JsonProperty("Email")
	private String emailId;

	@XmlElement(name = "Adrs")
	@JsonProperty("Adrs")
	private Address adrs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Address getAdrs() {
		return adrs;
	}

	public void setAdrs(Address adrs) {
		this.adrs = adrs;
	}
	
	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Employee toEmp(String jsonString) {
		
		ObjectMapper mapper = new ObjectMapper();
		Employee emp = null;
		try {
			emp = (Employee) mapper.readValue(jsonString, Employee.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//				mapper.convertValue(jsonString, Employee.class);
		
		return emp;
		
		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonFactory factory = mapper.getJsonFactory(); 
//		factory.createJsonParser(jsonString).
//		
//		
//		JsonParser jp = factory.createJsonParser(jsonString);
//		JsonNode actualObj = mapper.readTree(jp);
	}
	
	

}
