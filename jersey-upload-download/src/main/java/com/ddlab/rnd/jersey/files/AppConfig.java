package com.ddlab.rnd.jersey.files;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
	
	public AppConfig() {
		
		//For MultiPart, you have to register MultiPartFeature.class
		super(FileUploadDwonloadResource.class, JacksonFeature.class, MultiPartFeature.class);
		
	}

}
