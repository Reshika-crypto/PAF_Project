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
	
	
	//insert order details to the database
	public String insertOrder(String name, String value, String quantity) 
	{ 
		String output = "";
		
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
			String query = "insert into paf_project.order(OrderID, OrderName, OrderValue, OrderQuantity) values(?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values  
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, name); 
			preparedStmt.setDouble(3, Double.parseDouble(value)); 
			preparedStmt.setString(4, quantity); 
			
			preparedStmt.execute(); 
			con.close();
			
			output = "Order details are inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while inserting the Order details."; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	//update order details to the database
	public String updateOrder(String ID, String name, String value, String quantity)		
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 
			 String query = "UPDATE paf_project.order SET OrderName=?,OrderValue=?,OrderQuantity=? WHERE OrderID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setDouble(2, Double.parseDouble(value)); 
			 preparedStmt.setString(3, quantity); 
			 preparedStmt.setInt(4, Integer.parseInt(ID));
			 
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Order details are Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the Order details."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	//delete order details to the database
	public String deleteOrder(String OrderID) 
	{ 
		 	String output = ""; 
		 	try
		 	{ 
		 		Connection con = connect(); 
		 		if (con == null) 
		 		{return "Error while connecting to the database for deleting."; } 
		 
		 		String query = "delete from paf_project.order where OrderID=?"; 
		 		PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 		// binding values
		 		preparedStmt.setInt(1, Integer.parseInt(OrderID)); 
		 
		 		preparedStmt.execute(); 
		 		con.close(); 
		 		
		 		output = "Order details Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the Order details."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
} 		
