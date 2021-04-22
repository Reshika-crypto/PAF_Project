package com;

import model.Order;
import javax.ws.rs.*; 	//REST Service
import javax.ws.rs.core.MediaType; 	//REST Service

import com.google.gson.*;	//JSON

import org.jsoup.*;	//For XML
import org.jsoup.parser.*; 	//For XML
import org.jsoup.nodes.Document;	//For XML

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
 
