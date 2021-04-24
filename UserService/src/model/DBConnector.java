package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static final String host = "jdbc:mysql://127.0.0.1";
	private static final String port = "3306";
	private static final String dbname = "paf_project";
	private static final String username = "root";
	private static final String password = "";
	
	//Universal method to connect User service DB
	protected Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); 

			//MySQL Connection String
			con = DriverManager.getConnection(host+ ":" + port + "/" + dbname , username ,  password); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	}
}
