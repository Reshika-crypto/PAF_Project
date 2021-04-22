package com;

import model.Order;
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Order") 

public class OrderService 
{	
	//retrieve order details from the Order Table
	Order OrderObj = new Order(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readOrder() 
	 { 
	 return OrderObj.readOrder();
	 
	 }
}
 
