package com.ddlab.rnd.restful.webservices.jersey.server;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		packages(HelloWorld.class.getPackage().getName());
	}

}
