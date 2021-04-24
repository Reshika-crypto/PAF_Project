package model;
import java.sql.*;

public class ProductManagement 
{
	
	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/paf_project", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
	 
		return con;
	 }
	
	public String insertProduct(String pID, String pname, String pdesc, String pQuality, String price)
	 {
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
	 
				// create a prepared statement
								
				String query = "insert into paf_project.product (`product_id`,`product_name`,`product_description`,`product_quality`,`product_price`)"
								+ " values (?, ?, ?, ?, ?)";
	 
				PreparedStatement preparedStmt = con.prepareStatement(query);
	 
				// binding values
				preparedStmt.setString(0, pID);
				preparedStmt.setString(1, pname);
				preparedStmt.setString(2, pdesc);
				preparedStmt.setString(3, pQuality);
				preparedStmt.setDouble(4, Double.parseDouble(price));
	
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
	
	
		
} 