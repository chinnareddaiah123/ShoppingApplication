package com.shopping.services;

import com.shopping.dao.OrderDAO;
import com.shopping.dto.OrderDetails;

public class OrderService {
	OrderDAO orderDao=new OrderDAO();
	public void addOrderDetails(OrderDetails orderDetails)
	{
		
		if(orderDao.insertOrderDetails(orderDetails))
		{
			System.out.println("your order will "+orderDetails.getOrderAddress()+" Soon");
		}
		else
		{
			System.out.println("order details not inserted");
		}
	}

}
