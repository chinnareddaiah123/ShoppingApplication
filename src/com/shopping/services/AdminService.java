package com.shopping.services;

import java.util.Scanner;

import com.shopping.dao.AdminDAO;

public class AdminService {
	AdminDAO adminDAO=new AdminDAO();
	ProductService productService=new ProductService();
	Scanner s=new Scanner(System.in);
	public void adminLogin()
	{
		System.out.println("Enter the admin email id");
		String aEmail=s.next();
		System.out.println("Enter the password");
		String sPassword=s.next();
		if(adminDAO.getAdminDetails(aEmail, sPassword))
		{ boolean bool1=true;
		   while(bool1)
		   {
		   System.out.println("Enter yur choice \n 1.Enter product details \n 2.insert product based on brand");
		   int choice2=s.nextInt();
		   switch (choice2) {
		case 1:
		{
			productService.StoreProductDetails();
		}
			break;
		case 2:
		{
			productService.insertProductbasedOnBrand();
		}
		break;
		default:System.out.println("Enter the valid choice");
			break;
		}
		   System.out.println("Do you want to continue");
		   String str=s.next();
		   if(str.equalsIgnoreCase("yes"))
		   {
			   
		   }
		   else
		   {
			  bool1=false;
		   }
	   }
	   
		}
		else
		{
			System.out.println("invalid admin details");
		}
	} 
	

}
