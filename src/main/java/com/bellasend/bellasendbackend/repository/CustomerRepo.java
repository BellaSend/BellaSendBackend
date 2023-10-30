package com.bellasend.bellasendbackend.repository;

import com.bellasend.bellasendbackend.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer, String> {
}
