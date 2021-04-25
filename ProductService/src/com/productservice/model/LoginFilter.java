package com.productservice.model;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class LoginFilter implements ContainerRequestFilter{

	public static final String HEADER = "Authorization";
	public static final String PREFIX = "Basic";

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		try {

			List<String> requestHeaders = request.getRequestHeader(HEADER);

			if(requestHeaders == null || requestHeaders.size() <=0) {
				String responseStr = "Unauthorized Access. Authorization Header NOT found!";
				ResponseBuilder rb = Response.status(Response.Status.UNAUTHORIZED).entity(responseStr);
				throw new WebApplicationException(rb.build());
			}

			String basicAuthHeader = requestHeaders.get(0);

			if(basicAuthHeader==null) {
				String responseStr = "Unauthorized Access. Invalid Header!";
				ResponseBuilder rb = Response.status(Response.Status.UNAUTHORIZED).entity(responseStr);
				throw new WebApplicationException(rb.build());
			}

			String basicAuthWithoutPrefix = basicAuthHeader.split(" ")[1];
			String basicAuthDeoded = Base64.base64Decode(basicAuthWithoutPrefix);
			String username = basicAuthDeoded.split(":")[0];
			String password = basicAuthDeoded.split(":")[1];

			System.out.println("TEST::: UN: "+username + " PW: " + password);
			
			//interservice communication to login
			JsonObject credentialsJSON = new JsonObject();
			credentialsJSON.addProperty("username", username);
			credentialsJSON.addProperty("password", password);
			credentialsJSON.addProperty("service", "product");
			
			Client client = Client.create();
			WebResource wr = client.resource("http://localhost:8081/UserService/usermanagementservice/users/login");
			String output = wr.entity(credentialsJSON.toString(), MediaType.APPLICATION_JSON)
								.post(String.class);
			System.out.println("response: " +output);
			
			if (output.equalsIgnoreCase("true")) {
				return request;
			}

			String responseStr = "Unauthorized Access. Invalid Credentials!";
			ResponseBuilder rb = Response.status(Response.Status.UNAUTHORIZED).entity(responseStr);
			throw new WebApplicationException(rb.build());

		}catch(WebApplicationException e) {
			ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST).entity(e.getResponse().getEntity());
			throw new WebApplicationException(rb.build());
		}catch(Exception e) {
			String responseStr = "Exception occurred while processing the Authorization Header!";
			ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST).entity(responseStr);
			throw new WebApplicationException(rb.build());
		}

	}

}
