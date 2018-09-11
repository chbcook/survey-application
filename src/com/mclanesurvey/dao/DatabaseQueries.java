package com.mclanesurvey.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.codehaus.jettison.json.JSONArray;

import com.mclaneco.survey.model.Choice;
import com.mclaneco.survey.model.Question;
import com.mclaneco.survey.model.Question.Type;
import com.mclanesurvey.util.ToJSON;

public class DatabaseQueries extends EBSOracleConnection {
	
	public int insertIntoSurvey
		(
//			int SURVEY_ANSWER_ID, 
//			String CREATION_DATE, 
//			int CREATED_BY, 
//			String LAST_UPDATE_DATE,
//			int LAST_UPDATED_BY,
			
//			int SITE_NUMBER,
//			String CUSTOMER_EMAIL,
//			int QUESTION_ID,
//			int LOOKUP_ID,
//			int ANSWER_ID,
			String CAMPAIGN_GUID,
			int SURVEY_ID,
			String ANSWER_VALUE) 
		throws Exception {

			PreparedStatement query = null;
			Connection conn = null;
			
			try {
			
			conn = oracleDatabaseConnection();
			query = conn.prepareStatement("{call APPS.XXMCL_CS_SURV_JSON_PKG.GET_FILE(?, ?, ?)}");
//			+
//			"(SURVEY_ANSWER_ID, CREATION_DATE, CREATED_BY, LAST_UPDATE_DATE, LAST_UPDATED_BY, SURVEY_ID, SITE_NUMBER, CUSTOMER_EMAIL, QUESTION_ID, LOOKUP_ID, ANSWER_ID, ANSWER_VALUE, CAMPAIGN_GUID) " +
//			"VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			
//			query.setInt(1, SURVEY_ANSWER_ID);
//			query.setString(2, CREATION_DATE);
//			query.setInt(3, CREATED_BY);
//			query.setString(4, LAST_UPDATE_DATE);
//			query.setInt(5, LAST_UPDATED_BY);
			
//			query.setInt(7, SITE_NUMBER);
//			query.setString(8, CUSTOMER_EMAIL);
//			query.setInt(9, QUESTION_ID);
//			query.setInt(10, LOOKUP_ID);
//			query.setInt(11, ANSWER_ID);
			
			query.setString(1, CAMPAIGN_GUID);
			query.setInt(2, SURVEY_ID);
			query.setString(3, ANSWER_VALUE);
			
			query.executeUpdate();
			
			} catch(Exception e) {
			e.printStackTrace();
			return 500;
			}
			finally {
			if (conn != null) conn.close();
			}
			
			return 200;
			}
	
	public JSONArray queryReturnQuestions(String surveyid) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = oracleDatabaseConnection();
			query = conn.prepareStatement
					("SELECT question_id,question_name, question_type, "
					+ " LISTAGG(answer_display_value, ', ') WITHIN GROUP (ORDER BY answer_id) \"ANSWER_VALUES\" "
					+ " FROM IES_SVY_QUES_DATA "
					+ " WHERE SURVEY_ID=? "
					+ " GROUP BY question_id,question_name, question_type");
			
			query.setString(1, surveyid.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}
	public List<Question> createQuestions(String guid) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ArrayList<Question> ret = null;
		
		try {
			conn = oracleDatabaseConnection();
			query = conn.prepareStatement
					("SELECT qd.question_id,"
							+ "qd.question_name, "
							+ "qd.survey_id, "
							+ "qd.lookup_id, "
							+ "cd.creation_date, "
							+ "cd.created_by, "
							+ "cd.last_update_date, "
							+ "cd.last_updated_by, "
							+ "cd.site_number, "
							+ "cd.customer_email, "
							+ "cd.campaign_guid, "
							+ "q.question_type_id, "
							+ "qd.question_type, "
							+ "qd.answer_id, "
							+ "qd.answer_display_value"
//					+ " LISTAGG(answer_display_value, ', ') WITHIN GROUP (ORDER BY answer_id) \"ANSWER_VALUES\" "
//	+ " LISTAGG(answer_display_value, ', ') WITHIN GROUP (ORDER BY answer_id) \"ANSWER_VALUES\" "
					+ " FROM IES_SVY_QUES_DATA qd"
					+ " inner join ies_questions q"
					+ " on qd.question_id = q.question_id "
					+ " inner join xxmcl_survey_campaign_details cd"
					+ " on cd.survey_id = qd.survey_id "
					+ " and cd.campaign_guid=? "
					+ " order by qd.question_id ASC, qd.answer_id ");
			
			query.setString(1, guid.toUpperCase());
			ResultSet rs = query.executeQuery();
			ret = new ArrayList<>();
			while (rs.next()) {
				Question q = new Question();
				q.setId(rs.getInt("question_id"));
				int index = ret.indexOf(q);
				if (index >=0 ) {
					q = ret.get(index);	
				} else {
					q.setDescription(rs.getString("question_name"));
					q.setSurveyID(rs.getInt("survey_id"));
					q.setLookupID(rs.getInt("lookup_id"));
					q.setCreationDate(rs.getString("creation_date"));
					q.setCreatedBy(rs.getInt("created_by"));
					q.setLastUpdateDate(rs.getString("last_update_date"));
					q.setLastUpdatedBy(rs.getInt("last_updated_by"));
					q.setSiteNumber(rs.getInt("site_number"));
					q.setCustomerEmail(rs.getString("customer_email"));
					q.setCampaignGuid(rs.getString("campaign_guid"));
					Type type = new Type();
					type.setId(rs.getInt("question_type_id"));
					type.setDescription(rs.getString("question_type"));
					q.setType(type);
					q.setQuestion_choices(new ArrayList<>());
					ret.add(q);
				}
				Choice c = new Choice();
				c.setId(rs.getInt("answer_id"));
				c.setDescription(rs.getString("answer_display_value"));
				q.getQuestion_choices().add(c);
				
			}
			return ret;
		}

		finally {
			if (conn != null) conn.close();
		}
		
	}

}
