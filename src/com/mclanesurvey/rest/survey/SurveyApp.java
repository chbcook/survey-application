package com.mclanesurvey.rest.survey;

import java.util.List;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.mclaneco.survey.model.Question;
import com.mclanesurvey.dao.DatabaseQueries;


@Path("/surveyapp")
public class SurveyApp {
	
	@GET
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnQuestionsNew(
				@QueryParam("guid") String guid)
				throws Exception {
		
		List<Question> ret = null;
		
		try {
			
			if(guid == null) {
				return Response.status(400).entity("Error: please specify the Survey ID for this survey").build();
			}
			
			DatabaseQueries dao = new DatabaseQueries();
			
			ret = dao.createQuestions(guid);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(ret).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnQuestions(
				@QueryParam("surveyid") String surveyid)
				throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			if(surveyid == null) {
				return Response.status(400).entity("Error: please specify the Survey ID for this survey").build();
			}
			
			DatabaseQueries dao = new DatabaseQueries();
			
			json = dao.queryReturnQuestions(surveyid);
			returnString = json.toString();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSurveyData(String incomingData) throws Exception {
//		incomingData = incomingData.replace('"', '|');
//		incomingData = incomingData.replace(',', ' ');
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		DatabaseQueries dao = new DatabaseQueries();
		
		try {
			
			JSONObject surveyData = new JSONObject(incomingData);
			System.out.println( "jsonData: " + incomingData.toString() );
			
			int http_code = dao.insertIntoSurvey(
//														surveyData.optInt("SURVEY_ANSWER_ID"), 
//														surveyData.optString("CREATION_DATE"), 
//														surveyData.optInt("CREATED_BY"), 
//														surveyData.optString("LAST_UPDATE_DATE"), 
//														surveyData.optInt("LAST_UPDATED_BY"), 
														surveyData.optString("CAMPAIGN_GUID"), 
//														surveyData.optInt("SITE_NUMBER"), 
//														surveyData.optString("CUSTOMER_EMAIL"), 
//														surveyData.optInt("QUESTION_ID"), 
//														surveyData.optInt("LOOKUP_ID"), 
//														surveyData.optInt("ANSWER_ID"), 
														surveyData.optInt("SURVEY_ID"),
														surveyData.optString("ANSWER_VALUE"));
			
			if( http_code == 200 ) {
		
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Survey data has been entered successfully, Version 3");
				
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}
			
			System.out.println( "returnString: " + returnString );
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
}
