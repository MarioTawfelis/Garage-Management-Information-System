/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.JDBC;


public class Database		//Singleton Class
{

	private static final Database INSTANCE = new Database();		//Singleton construct
	private Connection connection = null;

	private Database()
	{
		if (connection != null )        //if a connection already exists
		{
		    return;
		}
		try
		{
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection("jdbc:sqlite:GMSISDB.db");
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
	}

	public static Database getInstance()
	{
		return INSTANCE;
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
	
	public ResultSet query(String sql)
	{
		ResultSet resultset = null;
		try
		{
			Statement statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return resultset;
	}
	
	public void update(String sql)
	{
		try
		{
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch (NullPointerException ne)
		{
			ne.printStackTrace();
		}
	}
}
