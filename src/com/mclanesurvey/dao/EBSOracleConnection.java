package com.mclanesurvey.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;
	
public class EBSOracleConnection {
	
	private static DataSource EBSOracleConnection = null;
	
	public static DataSource EBSOracleConnectionConn() throws Exception{
		Properties props = new Properties();
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream("C:/Users/Public/jag/workspaces/mars/survey-application/db.properties");
			props.load(fis);
			EBSOracleConnection = new OracleDataSource();
			((OracleDataSource) EBSOracleConnection).setURL(props.getProperty("ORACLE_DB_URL"));
			((OracleDataSource) EBSOracleConnection).setUser(props.getProperty("ORACLE_DB_USERNAME"));
			((OracleDataSource) EBSOracleConnection).setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return EBSOracleConnection;
	}
		
	protected static Connection oracleDatabaseConnection () {
		Connection conn = null;
		try {
			conn = EBSOracleConnectionConn().getConnection();
			return conn;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}

