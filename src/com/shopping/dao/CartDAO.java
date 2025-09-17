package com.shopping.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shopping.dto.CartDetails;
import com.shopping.util.JDBCConnection;

public class CartDAO {
	private static final String insert="insert into cart_details ( productID, customerID, productQuantity) values (?,?,?)";
	private static final String select="select productID from cart_details";
	public boolean insertcartDetails(CartDetails cartDetails)
	{
	  Connection connection;
	try {
		connection = JDBCConnection.forMySqlConnection();
		 PreparedStatement preparedStatement= connection.prepareStatement(insert);
		 preparedStatement.setInt(1, cartDetails.getProductID());
		 preparedStatement.setInt(2, cartDetails.getCustomerID());
		 preparedStatement.setInt(3, cartDetails.getProductQuantity());
		int result= preparedStatement.executeUpdate();
		if(result>0)
			
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
	public ArrayList<Integer> selectProductDetails()
	{
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			 Statement statement=connection.createStatement();
			ResultSet resultSet= statement.executeQuery(select);
			ArrayList<Integer> a1=new ArrayList<Integer>();
		  if(resultSet.isBeforeFirst())
		  {
			  while(resultSet.next())
			  {
				  a1.add( resultSet.getInt("productID"));
			  }
		  }
		  return a1;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
