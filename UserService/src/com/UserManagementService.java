package com;

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
@Path("/user-management") 

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
			@FormParam("password") String password)
	{ 
		String output = UserManagementObj.insertUserManagement(username, email, contactNo, address, password); 
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
		if(UserManagementObj.validateUser(credentialsJSON.get("username").getAsString(), credentialsJSON.get("password").getAsString())){
			return "true";
		} else {
			return "false";
		}
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
		String output = UserManagementObj.updateUserManagement(userID, username, email, contactNo, address, password); 
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
