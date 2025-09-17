package com.shopping.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shopping.dto.Paymentdetails;
import com.shopping.services.PaymentService;
import com.shopping.util.JDBCConnection;

public class PaymentDAO {
	private static final String insert="insert into payment_details (customerID, productID, paymentType, paymentStatus, totalAmount, paymentDate) values(?,?,?,?,?,?)";
	
	public boolean insertPaymentDetails(Paymentdetails paymentDetails)
	{
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(insert);
			prepareStatement.setInt(1,paymentDetails.getCustomerID());
			prepareStatement.setInt(2, paymentDetails.getProductID());
			prepareStatement.setString(3, paymentDetails.getPaymentType());
			prepareStatement.setString(4, paymentDetails.getPaymentStatus());
			prepareStatement.setDouble(5,paymentDetails.getTotalAmount());
			prepareStatement.setDate(6, Date.valueOf(paymentDetails.getPaymentDate()));
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
