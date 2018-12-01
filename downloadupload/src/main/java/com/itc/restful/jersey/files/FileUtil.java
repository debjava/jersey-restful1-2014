package com.itc.restful.jersey.files;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	
	public static byte[] readContents(String filePath) {
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
		}
		finally {
			try {
				if( inStream != null ) inStream.close();
			} catch (Exception e2) {
			}
		}
		return buffer;
	}
	
	public static byte[] readContents1(String filePath) {
		
		InputStream inStream = null;
		ByteArrayOutputStream outStream = null;
		try {
			inStream = new FileInputStream(filePath);
			outStream = new ByteArrayOutputStream();
			int c;
			while( (c = inStream.read()) != -1 ) {
				outStream.write(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if( inStream != null ) inStream.close();
				if( outStream != null ) outStream.close();
			} catch (Exception e2) {
			}
		}
		return outStream.toByteArray();
	}
	
	public static void saveFile(InputStream uploadedInputStream, String serverLocation) throws Exception {
		OutputStream outStream = null;
		outStream = new FileOutputStream(serverLocation);
		int read = 0;
		byte[] buffer = new byte[1024];
		while( (read = uploadedInputStream.read(buffer)) != -1 ) {
			outStream.write(buffer,0,read);
		}
		if( outStream != null) {
			outStream.flush();
			outStream.close();
		}
	}

}
