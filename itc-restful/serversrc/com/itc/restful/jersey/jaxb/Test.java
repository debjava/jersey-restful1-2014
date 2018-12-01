//package com.itc.restful.jersey.jaxb;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//
//public class Test {
//
//	public static void main(String[] args) throws Exception {
//		
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
//		
//		JAXBContext jaxb = JAXBContext.newInstance(Employee.class);
//		Marshaller marshaller = jaxb.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		marshaller.marshal(emp, System.out);
//	}
//
//}
