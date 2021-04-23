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
		
		
	//update order details in the Order table
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateOrder(String OrderData) 
		{ 
		//Convert the input string to a JSON object 
		 JsonObject OrderObject = new JsonParser().parse(OrderData).getAsJsonObject(); 
		 
		//Read the values
		 String OrderID = OrderObject.get("OrderID").getAsString(); 
		 String OrderName = OrderObject.get("OrderName").getAsString(); 
		 String OrderValue = OrderObject.get("OrderValue").getAsString(); 
		 String OrderQuantity = OrderObject.get("OrderQuantity").getAsString(); 
		 String output = OrderObj.updateOrder(OrderID,OrderName, OrderValue, OrderQuantity); 
		 return output; 
		}	
	
		
	//delete order details in the Order table
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteOrder(String OrderData) 
		{ 
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(OrderData, "", Parser.xmlParser()); 
	 
			//Read the value from the element <OrderID>
			String OrderID = doc.select("OrderID").text(); 
			String output = OrderObj.deleteOrder(OrderID); 
			return output; 
		}

		
}
 
