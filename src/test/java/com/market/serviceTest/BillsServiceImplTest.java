package com.market.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.market.dto.BillDto;
import com.market.dto.ProductsDto;
import com.market.service.impl.BillsServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BillsServiceImplTest {
	
	@InjectMocks
	BillsServiceImpl service;
	
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
	void cashiersBillTest() {
			
		assertNotNull(service.cashiersBill(productList)); 
	}
	
	@Test
	void productsQuantityTest() {
		
		ProductsDto response = service.productsQuantity(productList, "GR1");
		assertEquals(response.getProductQuantity(), 3);
		
	}
	
	@Test
	void buyProductGetProductfreeTest() {
		productsGr1.setProductQuantity(4);
		ProductsDto promoActive = service.buyProductGetProductfree(productsGr1, 2, 1);
		assertEquals(promoActive.getProductPrice(), 6.22);
		
		productsGr1.setProductQuantity(1);
		ProductsDto noPromo = service.buyProductGetProductfree(productsGr1, 2, 1);
		assertEquals(noPromo.getProductPrice(), 3.11);
		
		productsGr1.setProductQuantity(2);
		ProductsDto promoOne = service.buyProductGetProductfree(productsGr1, 2, 1);
		assertEquals(promoOne.getProductPrice(), 3.11);
		
		productsGr1.setProductQuantity(7);
		ProductsDto otherPromo = service.buyProductGetProductfree(productsGr1, 3, 2);
		assertEquals(otherPromo.getProductPrice(), 21.77);
		
	}
	
	@Test
	void forBulkPurchasesTest() {
		productsSr1.setProductQuantity(3);
		ProductsDto promoActive = service.forBulkPurchases(productsSr1, 3, 4.50);
		assertEquals(promoActive.getProductPrice(), 13.5);
		
	}
	
	@Test
	void promoPartPriceTest() {
		productsCf1.setProductQuantity(5);
		ProductsDto promoActive = service.promoPartPrice(productsCf1, 3);
		assertEquals(promoActive.getProductPrice(), 37.43333333333334);
	}
	

}
