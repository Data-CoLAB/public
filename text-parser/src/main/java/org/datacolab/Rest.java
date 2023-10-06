package org.datacolab;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * REST API Layer
 */
@Path("/")
@RequestScoped
public class Rest {

	@Inject
	Workflow flow;

	@Inject
	Utils utils;

	@GET
	@Path("/totalcharacters")
	@Operation(summary = "Get how many characters were already processed", description = "Dummy data")
	@APIResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = MediaType.APPLICATION_JSON))
	@Produces(MediaType.APPLICATION_JSON)
	public int getCounter() {
		return utils.getCounter();
	}

	@POST
	@Path("/process")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Pass an array of file names of files to process", description = "Dummy data")
	@APIResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class, type = SchemaType.ARRAY)))
	public String processFiles(List<String> fileNames) {
		try {
			for (String fileName : fileNames) {
				flow.processFile(fileName);
				System.out.println("File: " + fileName + ", processed correctly");
			}
		} catch (Exception e) {
			return "false";
		}
		return "success";
	}
}
