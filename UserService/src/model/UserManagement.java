package model;

import java.sql.*;

public class UserManagement extends DBConnector
{ 

	public String insertUserManagement( String uname, String uemail, String contactno, String uaddress, String upassword) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 

			// create a prepared statement
			String query = " insert into user (`userID`,`username`,`email`,`contactNo`,`address`,`password`)"
					+ " values (?, ?, ?, ?, ?,?)"; 

			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, uname); 
			preparedStmt.setString(3, uemail); 
			preparedStmt.setString(4, contactno); 
			preparedStmt.setString(5, uaddress);
			preparedStmt.setString(6, upassword);

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
					"<th>Address</th>"+
					"<th>Password</th></tr>"; 

			String query = "select * from user"; 
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
				String password = rs.getNString("password");

				// Add into the html table
				output += "<tr><td>" + userID + "</td>";
				output += "<td>" + username + "</td>"; 
				output += "<td>" + email + "</td>"; 
				output += "<td>" + contactNo + "</td>"; 
				output += "<td>" + address + "</td>";
				output += "<td>" + password + "</td>";

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
					"<th>Address</th>" +
					"<th>Password</th></tr>"; 

			String query = "select * from user where userID=?"; 
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
				String password = rs.getString("password");



				// Add into the html table
				rowOutput = "";
				output += "<tr><td>" + user_ID + "</td>";
				output += "<td>" + username + "</td>"; 
				rowOutput += "<td>" + email + "</td>"; 
				rowOutput += "<td>" + contactNo + "</td>"; 
				rowOutput += "<td>" + address + "</td>";
				rowOutput += "<td>" + password + "</td>";

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

	public String updateUserManagement(String uid, String uname, String uemail, int contactno, String address, String upassword)
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 

			if (con == null) 
			{return "Error while connecting to the database for updating."; } 

			// create a prepared statement
			String query = "UPDATE user SET username=?,email=?,contactNo=?,address=?,password=? WHERE userID=?"; 

			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values
			preparedStmt.setString(1, uname); 
			preparedStmt.setString(2, uemail); 
			preparedStmt.setInt(3, contactno); 
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, upassword);
			preparedStmt.setInt(6, Integer.parseInt(uid)); 

			// execute the statement
			int count = preparedStmt.executeUpdate(); 
			con.close(); 

			if(count > 0) {
				output = "Updated successfully"; 
			} else {
				output = "Update failed"; 
			}
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

			int count = preparedStmt.executeUpdate(); 
			con.close(); 

			if(count > 0) {
				output = "Deleted successfully"; 
			} else {
				output = "Delete failed"; 
			}
		} 

		catch (Exception e) 
		{ 
			output = "Error while deleting the user."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}


}




