package com.shopping.services;

import java.time.LocalDate;
import java.util.Scanner;

import com.shopping.dao.OrderDAO;
import com.shopping.dao.PaymentDAO;
import com.shopping.dto.OrderDetails;
import com.shopping.dto.Paymentdetails;
import com.shopping.dto.ProductDetails;

public class PaymentService {
Scanner s=new Scanner(System.in);
PaymentDAO paymentDao=new PaymentDAO();
Paymentdetails paymentDetails=new Paymentdetails();
OrderDetails orderDetails=new OrderDetails();
OrderService orderService=new OrderService();
public void paymentProcessDetails(int id,double amount,int productId,int quantity,String addresss)
{
	
	paymentDetails.setCustomerID(id);
	paymentDetails.setPaymentDate(LocalDate.now());
	System.out.println("Enter \n 1.For UPI \n 2.Cash on Delivery \n 3.Debit \n 4.Net Payment \n 5.EMI");
	switch (s.nextInt()) {
	case 1:
           paymentDetails.setPaymentType("Upi");
           paymentDetails.setPaymentStatus("payment succesfull");
		
		break;
	case 2:
		 paymentDetails.setPaymentType("cash on delivery");
		 paymentDetails.setPaymentStatus("Under processing");
    break;
	case 3:
		 paymentDetails.setPaymentType("Debit");
		 paymentDetails.setPaymentStatus("payment succesfull");
		 break;
	case 4:
		 paymentDetails.setPaymentType("Net Banking");
		 paymentDetails.setPaymentStatus("payment succesfull");
		 break;
	case 5:
		 paymentDetails.setPaymentType("EMI");
		 paymentDetails.setPaymentStatus("Under processing");
		 break;
	default:System.out.println("Enter the valid choice");
		break;
	}
	System.out.println("Enter the amount");
	double uamount=s.nextDouble();
	if(uamount==amount)
	{
	System.out.println("Payment details");
	paymentDetails.setCustomerID(id);
	paymentDetails.setProductID(productId);
	paymentDetails.setTotalAmount(amount);
	System.out.println(paymentDetails);
	}
	else
	{
		System.err.println("Invalid amount");
	}
	if(paymentDao.insertPaymentDetails(paymentDetails))
	{
		System.out.println("order placed succesfull");
		System.out.println("Select Address to deliver to product \n 1."+ addresss+" \n 2.Change address");
		String newAddress="";
		switch (s.nextInt()) {
		case 1 :
			newAddress=addresss;
			break;
		case 2:
		{
			System.out.println("Enter the address");
			String addres=s.next();
			newAddress=addres;
		}
		break;

		default:
			break;
		}
		orderDetails.setCustomerID(paymentDetails.getCustomerID());
		orderDetails.setOrderDate(paymentDetails.getPaymentDate());
		orderDetails.setProductID(paymentDetails.getProductID());
		orderDetails.setProductQuantity(quantity);
		
		orderDetails.setOrderAddress(newAddress);
		orderService.addOrderDetails(orderDetails);
		
		
	}
	else
	{
		System.out.println("Invalid error 505");
	}
}
}
