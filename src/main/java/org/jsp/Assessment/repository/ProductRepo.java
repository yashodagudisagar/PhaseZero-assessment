package org.jsp.Assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.jsp.Assessment.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	boolean existsByPartNumber(String partNumber);


}

