package com.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductsDto {

	private String productCode;
	private String productName;
	private double productPrice;
	private double productUnitPrice;
	private int productQuantity;
	
	
	public ProductsDto() {
		super();
	}
	
	
	
}
