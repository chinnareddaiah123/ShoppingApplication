package com.shopping.services;

import java.awt.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.shopping.dao.ProductDAO;
import com.shopping.dto.ProductDetails;

public class ProductService {
	ProductDAO productDAO=new ProductDAO();
	ProductDetails productDetails=new ProductDetails();
	Scanner s=new Scanner(System.in);

	public void StoreProductDetails()
	{
		//productID, productName, productBrand, productPrice, productManfDate, productExpDate, productQuantity, productCategory, productDiscount
		System.out.println("Enter the product name");
		String pname=s.next();
		System.out.println("enter the product brand");
		String pbrand=s.next();
		System.out.println("Enter the product price");
	     double pprice=s.nextDouble();
		System.out.println("Enter the product man facture date");
		String pmandate=s.next();
		System.out.println("Enter the product experi dats");
		String pExpdate=s.next();
		System.out.println("Enter product quantity");
		int pquantity=s.nextInt();
		System.out.println("Enter the product cateogeory");
		String pcategory=s.next();
		System.out.println("Enter the product discount");
		double pdiscount=s.nextDouble();
		productDetails.setProductName(pname);
		productDetails.setProductBrand(pbrand);
		productDetails.setProductCategory(pcategory);
		productDetails.setProductExpDate(Date.valueOf(pExpdate));
		productDetails.setProductManfDate(Date.valueOf(pmandate));
		productDetails.setProductPrice(pprice);
		productDetails.setProductQuantity(pquantity);
		productDetails.setProductDiscount(pdiscount);
		productDAO.insertProductDetails(productDetails);
		if(productDAO.insertProductDetails(productDetails))
		{
			System.out.println("Data ib=nserted succes full");
		}
		else
		{
			System.out.println("Invalid data eltered");
		}
	}
	public void insertProductbasedOnBrand()
	{
		ArrayList<ProductDetails> a1=new ArrayList<ProductDetails>();
		
		System.out.println("Enter the brand");
		String pbrand=s.next();
		System.out.println("Enter the no of items added on "+pbrand+" items");
		int count=s.nextInt();
		String n1[]= {"first","second","third","fourth","fifth","sixth","seventh"};
		for(int i=1;i<=count;i++)
		{
			ProductDetails productDetails=new ProductDetails();
			System.out.println("Enter the "+n1[i-1]+" product name");
			String pname=s.next();
			
			System.out.println("Enter the "+n1[i-1]+" product price");
		     double pprice=s.nextDouble();
			System.out.println("Enter the  "+n1[i-1]+" product man facture date");
			String pmandate=s.next();
			System.out.println("Enter the "+n1[i-1]+" product experi dats");
			String pExpdate=s.next();
			System.out.println("Enter "+n1[i-1]+" product quantity");
			int pquantity=s.nextInt();
			System.out.println("Enter "+n1[i-1]+" the product cateogeory");
			String pcategory=s.next();
			System.out.println("Enter "+n1[i-1]+" the product discount");
			double pdiscount=s.nextDouble();
			productDetails.setProductName(pname);
			productDetails.setProductBrand(pbrand);
			productDetails.setProductCategory(pcategory);
			productDetails.setProductExpDate(Date.valueOf(pExpdate));
			productDetails.setProductManfDate(Date.valueOf(pmandate));
			productDetails.setProductPrice(pprice);
			productDetails.setProductQuantity(pquantity);
			productDetails.setProductDiscount(pdiscount);
			a1.add(productDetails);
		
		}
	    if(  productDAO.insertMoreThanOneProduct(a1))
	    {
	    	System.out.println("Data inserted succes fully");
	    }
	    else
	    {
	    	System.out.println("server error");
	    }
		

	}
	public ArrayList<ProductDetails> allProductDetails()
	{
		return productDAO.printAllProductDetails();
	}
	
	

}
