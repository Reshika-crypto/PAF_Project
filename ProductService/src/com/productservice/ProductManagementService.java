package com.productservice;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.productservice.model.ProductManagement;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/products")

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
		 @FormParam("customer_id") int customer_id,
		 @FormParam("product_description") String product_description,
		 @FormParam("product_quality") String product_quality,
		 @FormParam("product_price") String product_price)
		{
		 String output = productObj.insertProduct(product_id, customer_id, product_name, product_description, product_quality, product_price);
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
		 int customer_id = productObject.get("customer_id").getAsInt();
		 String product_name = productObject.get("product_name").getAsString();
		 String product_description = productObject.get("product_description").getAsString();
		 String product_quality = productObject.get("product_quality").getAsString();
		 String product_price = productObject.get("product_price").getAsString();
		 String output = productObj.updateProduct(product_id, customer_id,product_name, product_description, product_quality, product_price);
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
		 String output = productObj.deleteProduct(product_id);
		return output;
		}
		
		
		@GET
		@Path("/{researcher_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String readProductByCustomerID(@PathParam("researcher_id") int researcher_id) {
			return productObj.readProductByCustomerID(researcher_id).toString();
		}


		
}


