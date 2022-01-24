package com.market.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.market.dto.BillDto;
import com.market.dto.ProductsDto;
import com.market.service.BillsService;

import lombok.SneakyThrows;

@Service
public class BillsServiceImpl implements BillsService {

	@SneakyThrows
	public BillDto cashiersBill(List<ProductsDto> products) {

		ProductsDto gr1 = productsQuantity(products, "GR1");
		ProductsDto sr1 = productsQuantity(products, "SR1");
		ProductsDto cf1 = productsQuantity(products, "CF1");

		return totalsBill(gr1, sr1, cf1);
	}

	@SneakyThrows
	public ProductsDto productsQuantity(List<ProductsDto> products, String type) {

		ProductsDto response = new ProductsDto();
		List<ProductsDto> unitProductLst = products.stream().filter(c -> c.getProductCode().equals(type))
				.collect(Collectors.toList());

		int quantity = unitProductLst.size();

		if (quantity == 0) {
			return response;
		}

		response.setProductCode(unitProductLst.get(0).getProductCode());
		response.setProductName(unitProductLst.get(0).getProductName());
		response.setProductUnitPrice(unitProductLst.get(0).getProductUnitPrice());
		response.setProductQuantity(quantity);

		return response;
	}

	/**
	 * this function is generic for promotions of the type buy n get n free
	 * @param product       = object
	 * @param promoQuantity = number of promotion rule
	 * @param promoPay      = total Number to pay at last
	 * @return productDto
	 */
	@SneakyThrows
	public ProductsDto buyProductGetProductfree(ProductsDto product, int promoQuantity, int promoPay) {

		int productQuantity = product.getProductQuantity();
		double promoPrice = 0;
		int count = 0;
		int productLeft = 0;

		if (productQuantity < promoQuantity) {
			product.setProductPrice(product.getProductUnitPrice());
			return product;
		}

		if (productQuantity == promoQuantity) {
			product.setProductPrice(promoPay * product.getProductUnitPrice());
			return product;
		}

		for (int a = promoQuantity; a <= productQuantity; a += promoQuantity) {
			count++;
			promoPrice = (product.getProductPrice() + product.getProductUnitPrice());

			if ((a + promoQuantity) > productQuantity) {
				productLeft = (productQuantity - (promoQuantity * count));
				if (productLeft == 0) {
					promoPrice = ((promoPay * count) * product.getProductUnitPrice());

				} else {
					if (productLeft < 0) {
						productLeft = (productLeft * -1);
					}
					promoPrice = ((promoPrice * promoPay) + (product.getProductUnitPrice() * productLeft));
				}
			}

			product.setProductPrice(promoPrice);

		}
		return product;

	}

	/**
	 * this function is generic to promotion for Bulk Purchases
	 * @param product       = type Object
	 * @param promoQuantity =  number of promotion rule
	 * @param newPrice      = is the new price of product
	 * @return productDto
	 */

	@SneakyThrows
	public ProductsDto forBulkPurchases(ProductsDto product, int promoQuantity, double newPrice) {
		if (product.getProductQuantity() >= promoQuantity) {
			product.setProductUnitPrice(newPrice);
		}
		double promoPrice = (product.getProductUnitPrice() * product.getProductQuantity());
		product.setProductPrice(promoPrice);

		return product;

	}

	/**
	 * this function is only to get the 2/3 of price on any product
	 * @param product       = type Object
	 * @param promoQuantity =  number of promotion rule
	 * 
	 */

	@SneakyThrows
	public ProductsDto promoPartPrice(ProductsDto product, int promoQuantity) {
		if (product.getProductQuantity() >= promoQuantity) {
			double newPrice = ((product.getProductUnitPrice() * 2) / 3);
			product.setProductUnitPrice(newPrice);
		}
		double promoPrice = (product.getProductUnitPrice() * product.getProductQuantity());
		product.setProductPrice(promoPrice);
		return product;

	}

	/**
	 * function create response bill with totals
	 * @param ProductsDto
	 * @return BillDto
	 **/
	public BillDto totalsBill(ProductsDto gr1, ProductsDto sr1, ProductsDto cf1) {

		BillDto response = new BillDto();
		List<ProductsDto> productsList = new ArrayList<>();
		double totalPrice = 0;

		if (gr1.getProductQuantity() != 0) {
			ProductsDto greenTea = buyProductGetProductfree(gr1, 2, 1);
			totalPrice += greenTea.getProductPrice();
			productsList.add(greenTea);
		}

		if (sr1.getProductQuantity() != 0) {
			ProductsDto strawberry = forBulkPurchases(sr1, 3, 4.50);
			totalPrice += strawberry.getProductPrice();
			productsList.add(strawberry);
		}
		if (cf1.getProductQuantity() != 0) {
			ProductsDto coffee = promoPartPrice(cf1, 3);
			totalPrice += coffee.getProductPrice();
			productsList.add(coffee);
		}
		response.setTotal(totalPrice);
		response.setProducts(productsList);
		return response;

	}

}
