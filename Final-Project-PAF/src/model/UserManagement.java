package model;

import java.sql.*;

public class UserManagement extends DBConnector
{ 
	
public String insertUserManagement( String uname, String uemail, String contactno, String uaddress) 
 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
			// create a prepared statement
			String query = " insert into user (`userID`,`username`,`email`,`contactNo`,`address`)"
					+ " values (?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, uname); 
			preparedStmt.setString(3, uemail); 
			preparedStmt.setString(4, contactno); 
			preparedStmt.setString(5, uaddress);
			
			// execute the statement3
			preparedStmt.execute(); 
			con.close();
			
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the user details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
 } 

public String readUserManagement() 
 { 
		String output = "";
		
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User ID</th><th>User Name</th><th>Email</th>" +
					"<th>Contact No</th>" + 
					"<th>Address</th></tr>"; 
 
			String query = "select * from paf_project.user"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String userID = Integer.toString(rs.getInt("userID")); 
				String username = rs.getString("username"); 
				String email = rs.getString("email"); 
				String contactNo =rs.getString("contactNo"); 
				String address = rs.getString("address");
				
				// Add into the html table
				output += "<tr><td>" + userID + "</td>";
				output += "<td>" + username + "</td>"; 
				output += "<td>" + email + "</td>"; 
				output += "<td>" + contactNo + "</td>"; 
				output += "<td>" + address + "</td>"; 
				
				// buttons
				output += "</tr>"; 
			} 
			con.close(); 
			
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the Users."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
 } 



public String readUserByID(String userID) 
{ 
		String output = "";
		
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{return "Error while connecting to the database for reading User."; } 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User ID</th><th>User Name</th><th>Email</th>" +
					"<th>Contact No</th>" + 
					"<th>Address</th></tr>"; 

			String query = "select * from paf_project.user where userID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, userID ); 
			ResultSet rs = preparedStmt.executeQuery();
			
			String rowOutput=null;
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String user_ID = Integer.toString(rs.getInt("userID")); 
				String username = rs.getString("username"); 
				String email = rs.getString("email"); 
				String contactNo =rs.getString("contactNo"); 
				String address = rs.getString("address");
				
				
				
				// Add into the html table
				rowOutput = "";
				output += "<tr><td>" + user_ID + "</td>";
				output += "<td>" + username + "</td>"; 
				rowOutput += "<td>" + email + "</td>"; 
				rowOutput += "<td>" + contactNo + "</td>"; 
				rowOutput += "<td>" + address + "</td>"; 
				
				// buttons
				rowOutput += "</tr>"; 
			} 
			con.close(); 
			
			// Complete the html table
			output +=rowOutput+ "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the User."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
} 

public String updateUserManagement(String uid, String uname, String uemail, int contactno, String address)
 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			
			// create a prepared statement
			String query = "UPDATE user SET username=?,email=?,contactNo=?,address=? WHERE userID=?"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, uname); 
			preparedStmt.setString(2, uemail); 
			preparedStmt.setInt(3, contactno); 
			preparedStmt.setString(4, address); 
			preparedStmt.setInt(5, Integer.parseInt(uid)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the user."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
 } 

public String deleteUserManagement(String userID) 
 { 
	String output = ""; 
	
	try
	{ 
		Connection con = connect();
		
		if (con == null) 
		{return "Error while connecting to the database for deleting."; } 
		
		// create a prepared statement
		String query = "delete from user where userID=?"; 
		
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(userID)); 
		
		// execute the statement
		preparedStmt.execute(); 
		con.close();
		
		output = "Deleted successfully"; 
	} 
	
	catch (Exception e) 
	{ 
		output = "Error while deleting the user."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
 	}


}

 
 

