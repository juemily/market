package com.market.service;

import java.util.List;

import com.market.dto.BillDto;
import com.market.dto.ProductsDto;

public interface BillsService {
	
	public  BillDto cashiersBill(List<ProductsDto> products);
	public  ProductsDto productsQuantity(List<ProductsDto> products, String type);
	public  ProductsDto buyProductGetProductfree(ProductsDto product, int promoQuantity, int promoPay);
	public  ProductsDto forBulkPurchases(ProductsDto product, int promoQuantity, double newPrice);
	public ProductsDto promoPartPrice(ProductsDto product, int promoQuantity);
	public BillDto totalsBill(ProductsDto gr1, ProductsDto sr1, ProductsDto cf1);

}
