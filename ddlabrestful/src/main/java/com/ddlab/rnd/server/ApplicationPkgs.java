package com.ddlab.rnd.server;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Debadatta Mishra
 *
 */
public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		super(EmpCreateService.class,JacksonFeature.class);
	}

}
