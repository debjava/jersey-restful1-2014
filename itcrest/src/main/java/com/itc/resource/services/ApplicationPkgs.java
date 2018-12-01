package com.itc.resource.services;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * The Class ApplicationPkgs.
 *
 * @author Debadatta Mishra
 */
public class ApplicationPkgs extends ResourceConfig {
	
	/**
	 * Instantiates a new application pkgs.
	 */
	public ApplicationPkgs() {
		super(LoginService.class,RegistrationService.class,JacksonFeature.class);
	}

}
