package com.itc.rest.test;

import org.codehaus.jackson.map.ObjectMapper;

import com.itc.models.Registration;
import com.itc.models.User;

public class ObjectConverter {
	
	public static void main(String[] args) throws Exception {
		
		User user = new User();
		user.setUserName("Deba");
		user.setPassword("deba");
		
		Registration r = new Registration();
		r.setId(1);
		r.setAge(23);
		r.setEmail("deba@gmail.com");
		r.setFirstName("John");
		r.setLastName("Abraham");
		r.setPassword("abcd1234");
		r.setPhoneNumber("12345");
		r.setUserName("john");
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, r);
	}

}
