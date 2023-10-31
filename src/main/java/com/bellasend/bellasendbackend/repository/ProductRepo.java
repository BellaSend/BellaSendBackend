package com.bellasend.bellasendbackend.repository;

import com.bellasend.bellasendbackend.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;

public interface ProductRepo extends MongoRepository<Product, String> {
    Page<Product> findProductsByProductCategoryNameAndPriceAndBrandName(
            String categoryName,
            BigDecimal price,
            String brandName,
            Pageable pageable
            );

    Page<Product> findProductsByProductCategoryNameAndPrice(
            String categoryName,
            BigDecimal price,
            Pageable pageable
    );

    Page<Product> findProductsByPriceAndBrandName(
            BigDecimal price,
            String brandName,
            Pageable pageable
    );

    Page<Product> findProductsByProductCategoryNameAndBrandName(
            String categoryName,
            String brandName,
            Pageable pageable
    );

    Page<Product> findProductsByPrice(BigDecimal price, Pageable pageable);
    Page<Product> findProductsByProductCategoryName(String categoryName, Pageable pageable);
    Page<Product> findProductsByBrandName(String brandName, Pageable pageable);
}
