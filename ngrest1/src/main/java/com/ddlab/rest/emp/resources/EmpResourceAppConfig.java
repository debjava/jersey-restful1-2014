package com.ddlab.rest.emp.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

//@ApplicationPath("/*")
@ApplicationPath("empresources")
public class EmpResourceAppConfig extends Application {
//There can be multiple Classes which extend javax.ws.rs.core.Application
//This is used for logical functional separation	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		resources.add(EmployeeResource.class);
		resources.add(MultiPartFeature.class);
		resources.add(JacksonFeature.class);
		return resources;
	}
}