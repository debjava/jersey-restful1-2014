package com.itc.restful.jersey.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 * @author Debadatta Mishra
 *
 */
@Path("itc")
public class ITCInfotechServices {
	
	private String filePath = "C:/TEMP/a.txt";

	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;


	@Path("/data")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getITCAddress() {
		
		return new String(readContents(filePath));
	}

	private byte[] readContents(String filePath) {

		File file = new File(filePath);
		byte[] buffer = new byte[(int) file.length()];
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(file);
			inStream.read(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null)
					inStream.close();
			} catch (Exception e2) {
			}
		}

		return buffer;

	}
}
