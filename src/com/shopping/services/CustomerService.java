package com.shopping.services;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import com.shopping.dao.CustomerDAO;
import com.shopping.dao.ProductDAO;
import com.shopping.dto.CartDetails;
import com.shopping.dto.CustomerDetails;
import com.shopping.dto.ProductDetails;
import com.shopping.exception.CustomerDataInvalidException;



public class CustomerService {
	CustomerDAO cDAO=new CustomerDAO();
	 // CustomerDetails customerDetails=new CustomerDetails();
	  ProductService productService=new ProductService();
	  CartService cartService=new CartService();
	  CustomerDetails customerDetails;
	  PaymentService paymentService=new PaymentService();
	Scanner s=new Scanner(System.in);
	public void customerRegistration()
	{
	
		System.out.println("Enter the customer name");
		String cname=s.next();
	  
		System.out.println("Enter the customer Email");
		String cemail=s.next();
		boolean bool=true;
		  for(CustomerDetails cde:cDAO.selectAllCustomerDetails())
		    {
		    	if(cde.getCustomerEmailID().equalsIgnoreCase(cemail))
		    	{
		    		bool=false;
		    	}
		    }
		  if(bool)
		  {
		while(true)
		{
		try {
		if(!cemail.contains("@gmail.com"))
		{
			throw new CustomerDataInvalidException();
		}
		else
		{
			break;
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Enter the valid email id");
			cemail=s.next();
		}
		}
		  }
		  else
		  {
			  throw new CustomerDataInvalidException("your email id already existed");
		  }
		ArrayList<CustomerDetails> listOfCustomers=cDAO.selectAllCustomerDetails();
		System.out.println("Enter the mobile number");
	   
	    while(true)
	    {
	    	 long cmobilenumber=s.nextLong();
	    long mNum=cmobilenumber;
		long mobilecount=listOfCustomers.stream().filter((customer)->customer.getCustomerMobileNo()==mNum).count();
		
		
			try {
				if(!(cmobilenumber>6000000000l &&cmobilenumber<9999999999l))
				{
					throw new CustomerDataInvalidException("Invalid mobile number");
				}
				if(mobilecount>0)
				{
					throw new CustomerDataInvalidException(" mobile number already existed");
				}
				else
				{
					 customerDetails.setCustomerMobileNo(cmobilenumber);
					break;
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(" reEnter the  mobile number");
				
			}
		
		
	    }
		
		System.out.println("Enter the the address");
		String caddress=s.next();
		System.out.println("Enter the customer gender");
		String cgender=s.next();
		System.out.println("Enter the customer password");
		String cpassword=s.next();
		long countOfPassword=listOfCustomers.stream().filter((customer)->customer.getCustomerPassword().equals(cpassword)).count();
		if(countOfPassword>0)
		{
			throw new CustomerDataInvalidException("password lready existed");
		}
		 
	    customerDetails.setCustomerName(cname);
	    customerDetails.setCustomerAddress(caddress);
	    customerDetails.setCustomerEmailID(cemail);
	    customerDetails.setCustomerGender(cgender);
	   
	    customerDetails.setCustomerPassword(cpassword);
	    if( cDAO.insertCustomerDetails(customerDetails))
	    {
	    	System.out.println("customer details inserted succes full");
	    }
	    
	}
	public void customerLogin()
	{
		System.out.println("Enter the email or mpobile number");
		String cEmailOrMobile=s.next();
		
		System.out.println("Ennter the password");
		String cpassword=s.next();
		//cDAO.selectCustomerDetailsByUsingEmailIdOrMobileNUmAndPassword(cEmailOrMobile, cpassword);
		 customerDetails=cDAO.selectCustomerDetailsByUsingEmailIdOrMobileNUmAndPassword(cEmailOrMobile, cpassword);
		
		if(customerDetails!=null)
		{
			System.out.println("login succes full");
			if(customerDetails.getCustomerGender().equalsIgnoreCase("male")) {
			System.out.println("Hello MR:"+customerDetails.getCustomerName());
			customerOperation();
			}
			if(customerDetails.getCustomerGender().equalsIgnoreCase("female")) {
				System.out.println("Hello MISS:"+customerDetails.getCustomerName());
				customerOperation();
				}
			
			
			
		}
		else
		{
			System.out.println("Invalid email id or password");
		}
	}
	public void  customerOperation()
	{
		boolean bool=true;
		while(true)
		{
		System.out.println("Enter \n 1.For all product details.\n 2. For Cart Details\n 3.For Order Details");
		int choice=s.nextInt();
		switch (choice) {
		case 1:
			ArrayList<ProductDetails> pd1=productService.allProductDetails();
			int n=1;
		 for(ProductDetails  pd:pd1)
		 {
			System.out.println("********************************************");
			System.out.println("S NO:"+n++);
			pd.display();
			System.out.println("********************************************");
		 }
			System.out.println("select s.no to add to cart or buy");
			ProductDetails productDetails=pd1.get(s.nextInt()-1);
			productDetails.display();
			
//			int num=s.nextInt();
//			int k=1;
//			 for(ProductDetails  pd:pd1)
//			 {
//				 
//				 if(k==num)
//				 {
//					 pd.display();
//				 }
//				 k++;
//				
//			 }
//			
			System.out.println("Enter \n 1.Add to cart. \n 2.To buy product");
			switch (s.nextInt()) {
			case 1:
				System.out.println("Add product to the cart");
				System.out.println("Enter the product quantity");
				int quantity=s.nextInt();
				 CartDetails cartDetails=new CartDetails();
				 cartDetails.setCustomerID(customerDetails.getCustomerID());
				cartDetails.setProductID(productDetails.getProductID());
				cartDetails.setProductQuantity(quantity);
				 cartService.addCartDetails(cartDetails);
				break;
			case 2:
				System.out.println("To buy product");
				//ProductDetails productDetails=new ProductDetails();
				 System.out.println("Enter the product Quantity");
				  int quanti=s.nextInt();
				 productDetails.setProductPrice(productDetails.getProductPrice()*quanti);
				 System.out.println("The product name"+productDetails.getProductName());
				 System.out.println("The product Brand is"+productDetails.getProductBrand());
				 System.out.println("The product price is"+productDetails.getProductPrice());
				 double discount=productDetails.getProductPrice()*((productDetails.getProductDiscount())/100);
				 System.out.println("The discount is"+discount);
				 double totalamount=productDetails.getProductPrice()-discount;
				 System.out.println("The total amount is"+totalamount);
				 paymentService.paymentProcessDetails(customerDetails.getCustomerID(),totalamount,productDetails.getProductID(),quanti,customerDetails.getCustomerAddress());
			default:System.out.println("Enter the valid choice");
				break;
			}
			break;
		case 2:
		{
			System.out.println("Thr cart details");
			cartService.AllproductDetails();
		}
		break;
		default:System.out.println("Enter the valid choice");
			break;
		}
		}
	}
	
		
	

}
