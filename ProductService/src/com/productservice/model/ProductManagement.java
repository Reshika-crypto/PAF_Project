package com.productservice.model;
import java.sql.*;

public class ProductManagement extends DBConnector
{
	
	
	
	public String insertProduct(String pID, String pname, String pdesc, String pQuality, String price)
	 {
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
	 
				// create a prepared statement
								
				String query = "insert into product (`product_id`,`product_name`,`product_description`,`product_quality`,`product_price`)"
								+ " values (?, ?, ?, ?, ?)";
	 
				PreparedStatement preparedStmt = con.prepareStatement(query);
	 
				// binding values
				preparedStmt.setString(1, pID);
				preparedStmt.setString(2, pname);
				preparedStmt.setString(3, pdesc);
				preparedStmt.setString(4, pQuality);
				preparedStmt.setDouble(5, Double.parseDouble(price));
	
				// execute the statement
				preparedStmt.execute();
				con.close();
				 
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				 
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	
	public String readProduct()
	{
			 
		String output = "";
			 
		try
		{
			 
			
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>" +
					"<th>Product Description</th>" +
					"<th>Product Quality</th>" +
					"<th>Product Price</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from product";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			 
			// iterate through the rows in the result set
			while (rs.next())
			{
								
				String product_id = rs.getString("product_id");
				String product_name = rs.getString("product_name");
				String product_description = rs.getString("product_description");
				String product_quality = rs.getString("product_quality");
				String product_price = Double.toString(rs.getDouble("product_price"));
			 
				// Add into the html table
				output += "<tr><td>" + product_id + "</td>";
				output += "<td>" + product_name + "</td>";
				output += "<td>" + product_description + "</td>";
				output += "<td>" + product_quality + "</td>";
				output += "<td>" + product_price + "</td>";
			 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='product.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							+ "<input name='productID' type='hidden' value='" + product_id
							+ "'>" + "</form></td></tr>";
			 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
		}
		catch (Exception e)
		{
			 output = "Error while reading the product.";
			 System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateProduct(String pID, String pname, String pdesc, String pQuality, String price)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
		 
			 // create a prepared statement
			 String query = "UPDATE product SET product_name=?,product_description=?,product_quality=?,product_price=? WHERE product_id=?";
		 
			PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 // binding values
			 preparedStmt.setString(5, pID);
			 preparedStmt.setString(1, pname);
			 preparedStmt.setString(2, pdesc);
			 preparedStmt.setString(3, pQuality);
			 preparedStmt.setDouble(4, Double.parseDouble(price));
		 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
		 
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String deleteProduct(String pID)
	{
		 
			String output = "";
		 
			try
			{
		 
				Connection con = connect();
		 
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
		 
				// create a prepared statement
		 
				String query = "delete from product where product_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				// binding values
				preparedStmt.setString(1,pID);
		 
				// execute the statement
				preparedStmt.execute();
				con.close();
		 
				output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 	output = "Error while deleting the product.";
			 	System.err.println(e.getMessage());
		 }
		 
		 return output;
	}
		
} 