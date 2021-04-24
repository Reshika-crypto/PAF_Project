package com;
import model.ProductManagement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/ProductManagement")

public class ProductManagementService 
	{
		ProductManagement productObj = new ProductManagement();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems()
		 {
		 return productObj.readProduct();
		 }
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertProduct(@FormParam("product_id") String product_id,
		 @FormParam("product_name") String product_name,
		 @FormParam("product_description") String product_description,
		 @FormParam("product_quality") String product_quality,
		 @FormParam("product_price") String product_price)
		{
		 String output = productObj.insertProduct(product_id, product_name, product_description, product_quality, product_price);
		 return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProduct(String productData)
		{
		//Convert the input string to a JSON object
		 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
		//Read the values from the JSON object
		 String product_id = productObject.get("product_id").getAsString();
		 String product_name = productObject.get("product_name").getAsString();
		 String product_description = productObject.get("product_description").getAsString();
		 String product_quality = productObject.get("product_quality").getAsString();
		 String product_price = productObject.get("product_price").getAsString();
		 String output = productObj.updateProduct(product_id, product_name, product_description, product_quality, product_price);
		return output;
		}


		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProduct(String productData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String product_id = doc.select("product_id").text();
		 System.out.println(product_id);
		 String output = productObj.deleteProduct(product_id);
		return output;
		}
}


