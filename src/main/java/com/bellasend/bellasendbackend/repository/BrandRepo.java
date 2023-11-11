package com.bellasend.bellasendbackend.repository;

import com.bellasend.bellasendbackend.domain.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepo extends MongoRepository<Brand, String> {
}
