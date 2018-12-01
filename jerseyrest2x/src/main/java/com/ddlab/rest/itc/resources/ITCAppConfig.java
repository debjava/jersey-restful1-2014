package com.ddlab.rest.itc.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("itcresources") //It becomes optional if you provide <url-pattern>/*</url-pattern> in web.xml
public class ITCAppConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		resources.add(ITCInfotechServices.class);
		resources.add(MultiPartFeature.class);
		resources.add(JacksonFeature.class);
		return resources;
	}
}