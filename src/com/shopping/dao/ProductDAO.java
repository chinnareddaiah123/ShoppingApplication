package com.shopping.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.shopping.dto.ProductDetails;
import com.shopping.util.JDBCConnection;

public class ProductDAO {
	private static final String printAllProducts="select * from product_details ";
	private static final String insert="insert into product_details (productName, productBrand, productPrice, productManfDate, productExpDate, productQuantity, productCategory, productDiscount) values(?,?,?,?,?,?,?,?)";
	public boolean insertProductDetails(ProductDetails productDetails)
	{
		 try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(insert);
			prepareStatement.setString(1,productDetails.getProductName());
			prepareStatement.setString(2,productDetails.getProductBrand());
			prepareStatement.setDouble(3,productDetails.getProductPrice());
			prepareStatement.setDate(4, productDetails.getProductManfDate());
			prepareStatement.setDate(5, productDetails.getProductExpDate());
			prepareStatement.setInt(6, productDetails.getProductQuantity());
			prepareStatement.setString(7, productDetails.getProductCategory());
			prepareStatement.setDouble(8, productDetails.getProductDiscount());
			
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
	public boolean insertMoreThanOneProduct(ArrayList<ProductDetails> listOfProducts)
	{
		
		try {
			Connection connection=JDBCConnection.forMySqlConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(insert);
			for(ProductDetails productDetails:listOfProducts )
			{
				prepareStatement.setString(1,productDetails.getProductName());
				prepareStatement.setString(2,productDetails.getProductBrand());
				prepareStatement.setDouble(3,productDetails.getProductPrice());
				prepareStatement.setDate(4, productDetails.getProductManfDate());
				prepareStatement.setDate(5, productDetails.getProductExpDate());
				prepareStatement.setInt(6, productDetails.getProductQuantity());
				prepareStatement.setString(7, productDetails.getProductCategory());
				prepareStatement.setDouble(8, productDetails.getProductDiscount());
				prepareStatement.addBatch();
			}
			int[] result=prepareStatement.executeBatch();
			if(result.length>0)
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
	public ArrayList<ProductDetails> printAllProductDetails()
	{
	  try {
		Connection connection=JDBCConnection.forMySqlConnection();
		PreparedStatement prepareStatement=connection.prepareStatement(printAllProducts);
		ResultSet resultSet=prepareStatement.executeQuery();
		ArrayList<ProductDetails> listOfProducts=new ArrayList<ProductDetails>();
		if(resultSet.isBeforeFirst())
		{
			while(resultSet.next())
			{
				ProductDetails productDetails=new ProductDetails();
				productDetails.setProductID(resultSet.getInt("productID"));
				productDetails.setProductName(resultSet.getString("productName"));
				productDetails.setProductBrand(resultSet.getString("productBrand"));
				productDetails.setProductPrice(resultSet.getInt("productPrice"));
				productDetails.setProductCategory(resultSet.getString("productCategory"));
				productDetails.setProductDiscount(resultSet.getDouble("productDiscount"));
				productDetails.setProductExpDate(resultSet.getDate("productExpDate"));
				productDetails.setProductManfDate(resultSet.getDate("productManfDate"));
				productDetails.setProductQuantity(resultSet.getInt("productQuantity"));
				listOfProducts.add(productDetails);
			}
		}
		return listOfProducts;
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	
	}
		
	}

}
