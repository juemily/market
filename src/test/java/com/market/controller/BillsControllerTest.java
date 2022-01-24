package com.market.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.market.controllers.BillsController;
import com.market.dto.BillDto;
import com.market.dto.ProductsDto;
import com.market.service.impl.BillsServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BillsControllerTest {
	
	@Mock
	BillsServiceImpl service;
	@InjectMocks
	BillsController controller;
	
	
	List<ProductsDto> productList = new ArrayList<>();
	ProductsDto productsGr1 = new ProductsDto();
	ProductsDto productsSr1 = new ProductsDto();
	ProductsDto productsCf1 = new ProductsDto();
	BillDto bill = new BillDto();
	
	@BeforeEach
    public void init() {
		productsGr1.setProductCode("GR1");
		productsGr1.setProductName("Green tea");
		productsGr1.setProductUnitPrice(3.11);
		productsSr1.setProductCode("SR1");
		productsSr1.setProductName("Strawberry");
		productsSr1.setProductUnitPrice(5.00);
		productsCf1.setProductCode("CF1");
		productsCf1.setProductName("Coffee");
		productsCf1.setProductUnitPrice(11.23);
		
		
		productList.add(productsGr1);
		productList.add(productsSr1);
		productList.add(productsCf1);
		productList.add(productsGr1);
		productList.add(productsGr1);
		
		bill.setProducts(productList);
		bill.setTotal(22.45);
       
    }
	
	@Test
	void billTest() {
		when(service.cashiersBill(productList)).thenReturn(bill);
		BillDto response = controller.bill(productList);
		assertEquals(response.getTotal(), 22.45);
		
	}

}
