package com.itc.restful.jersey.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/files")
public class FileUploadNDownload {

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "D:"+File.separator+"temp"+File.separator;
	private static final String SERVER_DOWNLOAD_LOCATION_FOLDER = "D:"+File.separator+"temp"+File.separator;
	private static final String FILE_NAME = "d.doc";
	private static final String IMG_FILE_NAME = "r.jpg";

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile( @FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
		// save the file to the server
		String output = "";
		try {
			FileUtil.saveFile(fileInputStream, filePath);
			output = "File saved to server location : " + filePath+" successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Unexpected server exception while uploading the file.";
		}
		return Response.status(200).entity(output).build();
	}

	@Path("/download")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile() {

		byte[] docStream = FileUtil.readContents(SERVER_DOWNLOAD_LOCATION_FOLDER+FILE_NAME); 
		String attachementStr =  "attachment; filename = "+FILE_NAME;
		return Response
				.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition",attachementStr)
				.build();
	}
	
	@Path("/download/img")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadImage() {

//		byte[] docStream = FileUtil.readContents1(SERVER_DOWNLOAD_LOCATION_FOLDER+FILE_NAME); 
//		String attachementStr =  "attachment; filename = "+IMG_FILE_NAME;
//		return Response
//				.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
//				.header("content-disposition",attachementStr)
//				.build();
		//http://localhost:8080/restfulbin/rest/files/download/img
		String attachementStr =  "attachment; filename = "+IMG_FILE_NAME;
		File file = new File(SERVER_DOWNLOAD_LOCATION_FOLDER+IMG_FILE_NAME);
		return Response.ok((Object) file).header("content-disposition",attachementStr).build();
	}
	
	@Path("/show/image")
	@GET
	@Produces("image/png")
	public Response showImage() {
		
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(SERVER_DOWNLOAD_LOCATION_FOLDER+IMG_FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Response.ok().entity(inStream).build();
	}
	
//	@Path("/download/image")
//	@GET
//	@Produces("image/png")
//	public Response getImage() {
//		InputStream inStream = null;
//		try {
//			inStream = new FileInputStream(SERVER_DOWNLOAD_LOCATION_FOLDER+IMG_FILE_NAME);
//			BufferedImage buffImage = ImageIO.read(inStream);
//			ByteArrayOutputStream bout = new ByteArrayOutputStream();
//			ImageIO.write(buffImage, "jpg", bout);
//			byte[] imgData = bout.toByteArray();
//			InputStream bigIn = new ByteArrayInputStream(imgData);
//			
//			return Response.ok(bigIn).build();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		catch(IOException ie) {
//			ie.printStackTrace();
//		}
//		return Response.noContent().build();
//	}


}
