package com.itc.restful.jersey.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Debadatta Mishra
 *
 */
@XmlRootElement(name = "Adrs")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

	@XmlElement(name = "DoorNo")
	@JsonProperty("DoorNo")
	private String doorNo;

	@XmlElement(name = "Street")
	@JsonProperty("Street")
	private String streetId;

	@XmlElement(name = "City")
	@JsonProperty("City")
	private String city;

	@XmlElement(name = "Country")
	@JsonProperty("Country")
	private String country;

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetId() {
		return streetId;
	}

	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
