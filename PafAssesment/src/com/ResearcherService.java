package com;

import model.Researcher;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Researchers")

public class ResearcherService {
	
	Researcher researcherObj = new Researcher(); 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readResearcher() 
		{ 
			return researcherObj.readResearcher(); 
		}
		
	//create post ---
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertResearcher(@FormParam("researcherId") String researcherId,
				@FormParam("name") String name,
				@FormParam("emailaddress") String emailaddress,
				@FormParam("workOnProduct") String workOnProduct,
				@FormParam("productCategory") String productCategory,
				@FormParam("purposeOfResearch") String purposeOfResearch,
				@FormParam("previousProducts") String previousProducts)
				{
				String output = researcherObj.insertResearcher(researcherId,name, emailaddress, workOnProduct, productCategory, purposeOfResearch, previousProducts);
				return output;
				}
		
		//create put ----
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateResearcher(String researcherData)
			{
				//Convert the input string to a JSON object
				JsonObject researcherObject = new JsonParser().parse(researcherData).getAsJsonObject();
		
				//Read the values from the JSON object
				String researcherId = researcherObject.get("researcherId").getAsString();
				String name = researcherObject.get("name").getAsString();
				String emailaddress = researcherObject.get("emailaddress").getAsString();
				String workOnProduct = researcherObject.get("workOnProduct").getAsString();
				String productCategory = researcherObject.get("productCategory").getAsString();
				String purposeOfResearch = researcherObject.get("purposeOfResearch").getAsString();
				String previousProducts= researcherObject.get("previousProducts").getAsString();
				
				String output = researcherObj.updateResearcher(researcherId, name, emailaddress, workOnProduct, productCategory, purposeOfResearch,previousProducts);
				return output;
			}
		
		//create delete ---
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteResearcher(String researcherData)
		{
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser());
		
		//Read the value from the element <researcherId>
		
		String researcherId = doc.select("researcherId").text().toString();
		String output = researcherObj.deleteResearcher(researcherId);
		return output;
		}
		
}// end of the ResearcherService.java class ---
