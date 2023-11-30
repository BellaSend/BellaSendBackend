package com.bellasend.bellasendbackend.repository;

import com.bellasend.bellasendbackend.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;

public interface ProductRepo extends MongoRepository<Product, String> {
    Page<Product> findProductsByProductCategoryIdAndPriceAndBrandId(
            String categoryId,
            BigDecimal price,
            String brandId,
            Pageable pageable
            );

    Page<Product> findProductsByProductCategoryIdAndPrice(
            String categoryId,
            BigDecimal price,
            Pageable pageable
    );

    Page<Product> findProductsByPriceAndBrandId(
            BigDecimal price,
            String brandId,
            Pageable pageable
    );

    Page<Product> findProductsByProductCategoryIdAndBrandId(
            String categoryId,
            String brandId,
            Pageable pageable
    );

    Page<Product> findProductsByPrice(BigDecimal price, Pageable pageable);
    Page<Product> findProductsByProductCategoryId(String categoryId, Pageable pageable);
    Page<Product> findProductsByBrandId(String brandId, Pageable pageable);
}
