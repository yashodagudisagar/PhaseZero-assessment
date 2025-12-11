package org.jsp.Assessment.service;

import java.util.List;

import jakarta.validation.Valid;
import org.jsp.Assessment.dto.ProductRequest;
import org.jsp.Assessment.entity.Product;

public interface ProductServiceInterface {

	Product addProduct(@Valid ProductRequest req);

	List<Product> listAll();

	List<Product> searchByName(String q);

	List<Product> filterByCategory(String category);

	List<Product> sortByPriceAsc();

	double inventoryValue();

}

