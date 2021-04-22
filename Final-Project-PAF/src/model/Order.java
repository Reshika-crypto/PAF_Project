package model;

import java.sql.*;

public class Order 
{ 	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/paf_project", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	
	public String readOrder() 
	 { 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for reading."; } 
			
				// Prepare the table to be displayed
				output = "<table border='1'><tr><th>Order Name</th>" +
						"<th>Order Value</th>" + 
						"<th>Order Quantity</th>" +
						"<th>Update</th><th>Remove</th></tr>"; 
	 
				String query = " select * from paf_project.order "; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
	 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String OrderID = Integer.toString(rs.getInt("OrderID")); 
					String OrderName = rs.getString("OrderName"); 
					String OrderValue = Double.toString(rs.getDouble("OrderValue")); 
					String OrderQuantity = rs.getString("OrderQuantity"); 
	 
					// Add into the table
					output += "<tr><td>" + OrderName + "</td>";  
					output += "<td>" + OrderValue + "</td>"; 
					output += "<td>" + OrderQuantity + "</td>"; 
	 
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='Order.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='OrderID' type='hidden' value='" + OrderID 
							+ "'>" + "</form></td></tr>"; 
				} 
				con.close(); 
	 
				// Complete the table
				output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the orders."; 
				System.err.println(e.getMessage()); 
			} 
			
			return output; 
	 } 
} 		
