package model;

import java.sql.*;

public class Order 
{ 	//connecting to the Database
	private Connection connect() 
	{ 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/paf_project", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	
	//retrieving order details from the database
	public String readOrder() 
	 { 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for reading."; } 
			
				// Table layout
				output = "<table border='1'><tr><th>Order Name</th>" +
						"<th>Order Value</th>" + 
						"<th>Order Quantity</th>" +
						"<th>Update</th><th>Remove</th></tr>"; 
	 
				String query = " select * from paf_project.order "; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
	 
				// Display each row from the table
				while (rs.next()) 
				{ 
					String OrderID = Integer.toString(rs.getInt("OrderID")); 
					String OrderName = rs.getString("OrderName"); 
					String OrderValue = Double.toString(rs.getDouble("OrderValue")); 
					String OrderQuantity = rs.getString("OrderQuantity"); 
	 
					// Inserting order details to the table to be displayed
					output += "<tr><td>" + OrderName + "</td>";  
					output += "<td>" + OrderValue + "</td>"; 
					output += "<td>" + OrderQuantity + "</td>"; 
	 
					// Update and Delete Buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='Order.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='OrderID' type='hidden' value='" + OrderID 
							+ "'>" + "</form></td></tr>"; 
				} 
				con.close(); 
	 
				// Display the completed table
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
