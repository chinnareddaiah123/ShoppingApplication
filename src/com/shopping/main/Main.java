package com.shopping.main;

import java.util.Scanner;

import com.shopping.services.AdminService;
import com.shopping.services.CustomerService;
import com.shopping.services.ProductService;

public class Main {
	public static void main(String[] args) {
		CustomerService cs=new CustomerService();
		AdminService as=new AdminService();
		
		Scanner s=new Scanner(System.in);
//		String st="***----***Welcome to A14_Shopping****-----****";
//		for(int i=0;i<st.length();i++)
//		{
//			char ch=st.charAt(i);
//			try {
//				Thread.sleep(400);
//				System.out.print(ch);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		System.out.println();
		boolean bool=true;
		while(bool)
		{
			System.out.println("Enter \n 1.For Admin login. \n 2.For Customer Registration. \n 3.For Customer login.");
		switch(s.nextInt())
		{
		case 1:
			System.out.println("Admin Login succesful");
			as.adminLogin();
		  
		case 2:
			System.out.println("Customer Registration");
			cs.customerRegistration();
			break;
		case 3:
			System.out.println("Customer login");
			cs.customerLogin();
			break;
		
			default:
			System.out.println("Invalid request");
			break;
		}
		System.out.println("Dou you want to continue 😊😊😊 \n Yes \n No");
		String s1=s.next();
		if(s1.equalsIgnoreCase("yes"))
		{
			bool=true;
		}
		else
		{
			bool=false;
		}
	}
		System.out.println("Thank you visie again....😊😊😊");
		}


	

}
