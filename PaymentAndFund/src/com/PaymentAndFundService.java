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
}