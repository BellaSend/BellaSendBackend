package com.bellasend.bellasendbackend.repository;

import com.bellasend.bellasendbackend.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {
}
