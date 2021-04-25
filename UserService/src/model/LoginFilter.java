package model;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.UserManagementService;
import com.google.gson.JsonObject;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class LoginFilter implements ContainerRequestFilter {

	public static final String HEADER = "Authorization";
	public static final String PREFIX = "Basic";
	private UserManagementService user = new UserManagementService();

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		try {
			//allow sign up for anyone
			if((request.getPath().equals("users") || request.getPath().equals("users/login")) && request.getMethod().equals("POST")) {
				return request;
			}

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
			
			JsonObject credentials = new JsonObject();
			credentials.addProperty("username", username);
			credentials.addProperty("password", password);
			credentials.addProperty("service", "user");
			
			
			if (user.userLogin(credentials.toString()).equalsIgnoreCase("true")) {
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
