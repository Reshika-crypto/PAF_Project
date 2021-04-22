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
	
	
	//insert order details into the Order table
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertOrder(@FormParam("OrderName") String OrderName,
									@FormParam("OrderValue") String OrderValue,
									@FormParam("OrderQuantity") String OrderQuantity) 
		
		{ 
		 String output = OrderObj.insertOrder(OrderName, OrderValue, OrderQuantity); 
		 return output; 
		}
		
	
	
}
 
