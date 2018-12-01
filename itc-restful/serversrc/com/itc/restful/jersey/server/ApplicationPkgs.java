package com.itc.restful.jersey.server;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
//		super(ITCInfotech.class,JacksonFeature.class,MyObjectMapperProvider.class);
		super(ITCInfotech.class,JacksonFeature.class);
//		packages(ITCInfotech.class);
	}

}
