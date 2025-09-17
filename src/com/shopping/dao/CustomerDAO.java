package com.shopping.dao;



import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;
import com.shopping.dto.CustomerDetails;
import com.shopping.util.JDBCConnection;

public class CustomerDAO {
	
	private static final String select="Select * from customer_details";
	private static final String insert="insert into customer_details (customerName ,customerEmailID, customerMobileNo, customerAddress,customerGender,customerPassword) values(?,?,?,?,?,?)";
	private static final String customerLogin="select * from customer_details where (customerEmailID=? or customerMobileNo=?) and customerPassword=?";
	public boolean insertCustomerDetails(CustomerDetails customerDetails)
	{
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(insert);
			prepareStatement.setString(1,customerDetails.getCustomerName());
			prepareStatement.setString(2, customerDetails.getCustomerEmailID());
			prepareStatement.setLong(3, customerDetails.getCustomerMobileNo());
			prepareStatement.setString(4, customerDetails.getCustomerAddress());
			prepareStatement.setString(5, customerDetails.getCustomerGender());
			prepareStatement.setString(6,customerDetails.getCustomerPassword());
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
	public ArrayList<CustomerDetails> selectAllCustomerDetails()
	{
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(select);
			ResultSet resultSet=prepareStatement.executeQuery();
		   ArrayList<CustomerDetails> listOfCustomers=new ArrayList<CustomerDetails>();
			while(resultSet.next())
			{
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setCustomerEmailID(resultSet.getString("customerEmailID"));
				customerDetails.setCustomerMobileNo(resultSet.getLong("customerMobileNo"));
				customerDetails.setCustomerPassword(resultSet.getString("customerPassword"));
				listOfCustomers.add(customerDetails);
			}
			return listOfCustomers;
		} catch (ClassNotFoundException |  SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  return null;
		}
	}
	public CustomerDetails  selectCustomerDetailsByUsingEmailIdOrMobileNUmAndPassword(String emailOrMobileNum,String password)
	{
		/* select * from customer_details where (customerEmailID=? or customerEmailID=?) and customerPassword=?*/
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(customerLogin);
			prepareStatement.setString(1, emailOrMobileNum);
			prepareStatement.setString(2, emailOrMobileNum);
			prepareStatement.setString(3, password);
			ResultSet resultSet= prepareStatement.executeQuery();
		/*	if(resultSet.next())
			{
				
				if(resultSet.getString("customerGender").equalsIgnoreCase("male"))
				{
					System.out.println("Login succes full");
					System.out.println("Hello MR:"+resultSet.getString("customerName"));
					System.out.println("Welcome to e-commerce express");
				}
				else
				{
					System.out.println("Login succes full");
					System.out.println("Hello Miss:"+resultSet.getString("customerName"));
					System.out.println("Welcome to e-commerce express");
				}
				
			}
			else
			{
				System.out.println("Invalid email or password");
			}*/
			//(or)
			if(resultSet.next())
			{
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setCustomerID(resultSet.getInt("customerID"));
				customerDetails.setCustomerEmailID(resultSet.getString("customerEmailID"));
				customerDetails.setCustomerName(resultSet.getString("customerName"));
				customerDetails.setCustomerGender(resultSet.getString("customerGender"));
				customerDetails.setCustomerMobileNo(resultSet.getLong("customerMobileNo"));
				customerDetails.setCustomerPassword(resultSet.getString("customerPassword"));
				customerDetails.setCustomerAddress(resultSet.getString("customerAddress"));
				return customerDetails;
				
			}
			else
			{
				return null;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}
			

}

