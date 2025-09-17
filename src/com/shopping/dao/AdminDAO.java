package com.shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shopping.util.JDBCConnection;

public class AdminDAO {
	private static final String adminDetails="select * from admin_details where adminEmailID=? and adminPassword=?";
	public boolean getAdminDetails(String Email,String password)
	{
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(adminDetails);
			prepareStatement.setString(1, Email);
			prepareStatement.setString(2, password);
			ResultSet resultSet=prepareStatement.executeQuery();
		    if(resultSet.next())
		    {
		    	return true;
		    }
		    else
		    {
		    	return false;
		    }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
