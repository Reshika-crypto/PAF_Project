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
}