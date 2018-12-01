package com.ddlab.rest.resources;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;


public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		super(LoginServiceResource.class,ITCInfotechServices.class,JacksonFeature.class);
	}

}
