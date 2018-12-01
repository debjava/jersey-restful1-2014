package com.itc.restful.jersey.server;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		super(ITCInfotechServices.class,JacksonFeature.class);
	}

}
