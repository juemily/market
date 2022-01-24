package com.market.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillDto {
	
	private double total;
	private List<ProductsDto> products;
	public BillDto() {
		super();
	}
	
	

}
