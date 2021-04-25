package com;

import model.UserAccount;
import model.UserManagement;

//For Rest Services
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/users") 

public class UserManagementService 
{ 
	UserManagement UserManagementObj = new UserManagement();

	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML)

	public String readUserManagement() 
	{ 
		return UserManagementObj.readUserManagement();
	}

	@GET
	@Path("/{user_ID}") 
	@Produces(MediaType.TEXT_HTML)

	public String readUserManagement(@PathParam("user_ID") String userid) 
	{ 
		return UserManagementObj.readUserByID(userid);
	}

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertUserManagement(@FormParam("username") String username, 
			@FormParam("email") String email, 
			@FormParam("contactNo") String contactNo, 
			@FormParam("address") String address,
			@FormParam("password") String password,
			@FormParam("user_type") String user_type)
	{ 
		String output = UserManagementObj.insertUserManagement(username, email, contactNo, address, password, user_type); 
		return output; 
	}

	@POST
	@Path("/login") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String userLogin(String credentials)
	{ 
		try {
		JsonObject credentialsJSON = new JsonParser().parse(credentials).getAsJsonObject();

		if(!(credentialsJSON.has("username") || credentialsJSON.has("password") || credentialsJSON.has("service") )) {
			return "false";
		}
		
		UserAccount userAccount = UserManagementObj.validateUser(credentialsJSON.get("username").getAsString(), credentialsJSON.get("password").getAsString());
		
		if(userAccount == null) {
			return "false";
		}

		String service = credentialsJSON.get("service").getAsString().toLowerCase();
		
		if(service.equalsIgnoreCase("research")) {
			if(!(userAccount.getUser_type().toLowerCase().equalsIgnoreCase("admin") || userAccount.getUser_type().toLowerCase().equalsIgnoreCase("researcher") || userAccount.getUser_type().toLowerCase().equalsIgnoreCase("funder"))) {
				return "false";
			}
			return "true";
		} else if(service.equalsIgnoreCase("product")) {
			if(!(userAccount.getUser_type().toLowerCase().equalsIgnoreCase("admin") || userAccount.getUser_type().toLowerCase().equalsIgnoreCase("researcher") || userAccount.getUser_type().toLowerCase().equalsIgnoreCase("buyer"))) {
				return "false";
			}
			return "true";
		} else if(service.equalsIgnoreCase("order")) {
			if(!(userAccount.getUser_type().toLowerCase().equalsIgnoreCase("admin") || userAccount.getUser_type().toLowerCase().equalsIgnoreCase("buyer"))) {
				return "false";
			}
			return "true";
		} else if(service.equalsIgnoreCase("payment")) {
			if(!(userAccount.getUser_type().toLowerCase().equalsIgnoreCase("admin") || userAccount.getUser_type().toLowerCase().equalsIgnoreCase("buyer") || userAccount.getUser_type().toLowerCase().equalsIgnoreCase("funder"))) {
				return "false";
			}
			return "true";
		} else if(service.equalsIgnoreCase("user")) {
			if(!(userAccount.getUser_type().toLowerCase().equalsIgnoreCase("admin"))) {
				return "false";
			}
			return "true";
		}
		
		return "false";
		
		} catch (Exception e) {
			System.out.println(e);
			return "false";
		}
	}

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUserManagement(String userData) 

	{ 
		//Convert the input string to a JSON object 
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();

		//Read the values from the JSON object
		String userID = userObject.get("userID").getAsString(); 
		String username = userObject.get("username").getAsString(); 
		String email = userObject.get("email").getAsString(); 
		int contactNo = userObject.get("contactNo").getAsInt(); 
		String address = userObject.get("address").getAsString();
		String password = userObject.get("password").getAsString();
		String user_type = userObject.get("user_type").getAsString();
		String output = UserManagementObj.updateUserManagement(userID, username, email, contactNo, address, password, user_type); 
		return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUserManagement(String userData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 

		//Read the value from the element <itemID>
		String userID = doc.select("userID").text();

		String output = UserManagementObj.deleteUserManagement(userID); 
		return output; 
	}


}
