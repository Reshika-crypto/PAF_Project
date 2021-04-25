package com;

import model.PaymentAndFund; 

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")

public class PaymentAndFundService {
	PaymentAndFund PayObj = new PaymentAndFund();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PayObj.readPayment();
	}
	
	//insert

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("pId") String pId, 
			@FormParam("prName") String prName,
			@FormParam("pyDate") String pyDate,
			@FormParam("pyAmount") String pyAmount) {
		String output = PayObj.insertPayment(pId, prName, pyDate, pyAmount);
		return output;
	}
	//update
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject PayObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String pId = PayObject.get("pId").getAsString();
		String prName = PayObject.get("prName").getAsString();
		String pyDate = PayObject.get("pyDate").getAsString();
		String pyAmount = PayObject.get("pyAmount").getAsString();
		
		String output = PayObj.updatePayment(pId, prName, pyDate, pyAmount);
		return output;
	}
	//delete 
	//part
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String pId = doc.select("pId").text();
		String output = PayObj.deletePayment(pId);
		return output;
	}
}