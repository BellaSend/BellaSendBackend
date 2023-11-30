package com.bellasend.bellasendbackend.repository;

import com.bellasend.bellasendbackend.domain.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductCategoryRepo extends MongoRepository<ProductCategory, String> {
}
