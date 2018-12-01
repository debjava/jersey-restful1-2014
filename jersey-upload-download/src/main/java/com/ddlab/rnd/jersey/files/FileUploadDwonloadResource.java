package com.ddlab.rnd.jersey.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.itc.restful.jersey.entity.Employee;
import com.itc.restful.jersey.entity.FileUtil;

@Path("/files")
public class FileUploadDwonloadResource {

	public static final String SERVER_UPLOAD_LOCATION_FOLDER = "D:/temp/";

	@POST
	@Path("/upload1")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form) {
		// http://localhost:8080/jersey-upload-download/rest/files/upload1
		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER
				+ headerOfFilePart.getFileName();
		// save the file to the server
		FileUtil.saveFile(fileInputStream, filePath);
		String output = "File saved to server location using FormDataMultiPart : "
				+ filePath;
		return Response.status(200).entity(output).build();
	}

	// Another way of file uploading

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		// http://localhost:8080/jersey-upload-download/rest/files/upload
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER
				+ contentDispositionHeader.getFileName();
		// save the file to the server
		String output = "";
		try {
			FileUtil.saveFile(fileInputStream, filePath);
			output = "File saved to server location : " + filePath
					+ " successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Unexpected server exception while uploading the file.";
		}
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/upload2")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(FormDataMultiPart form,
			@FormDataParam("emp") String emp) {

		// http://localhost:8080/jersey-upload-download/rest/files/upload2
		System.out.println("Employee-------->" + emp.toString());
		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER
				+ headerOfFilePart.getFileName();
		// save the file to the server
		FileUtil.saveFile(fileInputStream, filePath);
		String output = "File saved to server location using FormDataMultiPart : "
				+ filePath;
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/upload3")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFileWithData(FormDataMultiPart form) {
		// In this case, you extract the emp key from the
		// http://localhost:8080/jersey-upload-download/rest/files/upload3
		Map<String, List<FormDataBodyPart>> params = form.getFields();

		System.out.println(params);

		String myJson = params.get("emp").get(0).getValue();
		System.out.println("myjson---------->" + myJson);
		Employee emp = Employee.toEmp(myJson);
		System.out.println("emp-------->" + emp);

		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER
				+ headerOfFilePart.getFileName();
		// save the file to the server
		FileUtil.saveFile(fileInputStream, filePath);
		String output = "File saved to server location using FormDataMultiPart : "
				+ filePath;
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/multiupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMultipleFile(FormDataMultiPart form) {
		// http://localhost:8080/jersey-upload-download/rest/files/multiupload

		BodyPartEntity bodyPartEntity;
		for (BodyPart part : form.getBodyParts()) {
			bodyPartEntity = (BodyPartEntity) part.getEntity();
			String fileName = part.getContentDisposition().getFileName();
			System.out
			.println("part.getContentDisposition().getFileName()---------->"
					+ fileName);
			InputStream fileInputStream = bodyPartEntity.getInputStream();
			String filePath = SERVER_UPLOAD_LOCATION_FOLDER + fileName;
			FileUtil.saveFile(fileInputStream, filePath);
		}
		return Response.status(200)
				.entity("All Files uploaded successfully ... ").build();
	}

	@Path("/download")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@QueryParam("fileName") String fileName) {
		// Do not provide path param
		Response response = null;
		String dirPath = "D:/temp";
		String filePath = dirPath + File.separator + fileName;
		try {
			if (!new File(filePath).exists())
				throw new FileNotFoundException();
			byte[] docStream = FileUtil.readContents(dirPath + File.separator
					+ fileName);
			String attachment = "attachment; filename = " + fileName;
			response = Response
					.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
					.header("content-disposition", attachment).build();

		} catch (FileNotFoundException e) {
			response = Response.serverError().status(Status.BAD_REQUEST)
					.build();
		}
		return response;
	}

	@Path("/show/image")
	@GET
	@Produces("image/jpg")
	public Response showImage(@QueryParam("fileName") String fileName) {

		Response response = null;
		String dirPath = "D:/temp";
		InputStream inStream = null;
		String filePath = dirPath + File.separator + fileName;
		try {
			if (!new File(filePath).exists())
				throw new FileNotFoundException();
			inStream = new FileInputStream(filePath);
			response = Response.ok().entity(inStream).build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response = Response.serverError().status(Status.BAD_REQUEST)
					.build();
		}
		return response;
	}

}
