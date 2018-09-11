package com.mclanesurvey.rest.status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import com.mclanesurvey.dao.*;

@Path("/surveyappstatus")
public class SurveyAppStatus {

	private static final String api_version = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Survey Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
		
			conn = EBSOracleConnection.EBSOracleConnectionConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME " + "from sys.dual");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				myString = rs.getString("DATETIME");
			}
			
			query.close();
			
			returnString = "<p>Database Status</p> " + "<p>Database Date/Time return: " + myString + "</p>";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
	
	return returnString;
}
	}
