package com.shopping.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shopping.dto.OrderDetails;
import com.shopping.util.JDBCConnection;

public class OrderDAO {
	private final String insert="insert into order_details ( customerID, productID, productQuantity, orderAddress, orderDate) values (?,?,?,?,?)";
	public boolean insertOrderDetails(OrderDetails orderDetails)
	{
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(insert);
			prepareStatement.setInt(1,orderDetails.getCustomerID() );
			prepareStatement.setInt(2, orderDetails.getProductID());
			prepareStatement.setInt(3, orderDetails.getProductQuantity());
			prepareStatement.setString(4, orderDetails.getOrderAddress());
			prepareStatement.setDate(5, Date.valueOf(orderDetails.getOrderDate()));
			int result=prepareStatement.executeUpdate();
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

}
