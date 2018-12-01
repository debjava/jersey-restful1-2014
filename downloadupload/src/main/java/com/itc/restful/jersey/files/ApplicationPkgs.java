package com.itc.restful.jersey.files;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		super(FileUploadNDownload.class,MultiPartFeature.class);
	}

}
