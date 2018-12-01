package com.itc.restful.jersey.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name="Emp")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
	
	@XmlElement(name="Name")
	@JsonProperty("Name")
	private String name;
	
	@XmlElement(name="Age") 
	@JsonProperty("Age") 
	private int age;
	
	@XmlElement(name="Email") 
	@JsonProperty("Email") 
	private String emailId;
	
	@XmlElement(name="Adrs") 
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

}
