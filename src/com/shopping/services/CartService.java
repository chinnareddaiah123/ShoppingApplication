package com.shopping.services;

import com.shopping.dao.CartDAO;
import com.shopping.dto.CartDetails;
import com.shopping.dto.ProductDetails;

public class CartService {
	CartDAO cartDAO=new CartDAO();
	ProductService productService=new ProductService();
	public void addCartDetails(CartDetails cartDetails)
	{
		if(cartDAO.insertcartDetails(cartDetails))
		{
			System.out.println("Product added to cart");
		}
		else
		{
			System.out.println("server error");
		}
	}
   public void AllproductDetails()
   {
	   for(Integer id:cartDAO.selectProductDetails())
	   {
	 for(ProductDetails pd:  productService.allProductDetails())
	 {
		if( pd.getProductID()==id)
		{
			pd.display();
			System.out.println("...........................................");
		}
	 }
	   }
   }
}
