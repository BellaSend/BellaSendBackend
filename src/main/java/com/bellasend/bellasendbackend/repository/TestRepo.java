package com.bellasend.bellasendbackend.repository;

import com.bellasend.bellasendbackend.domain.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepo extends MongoRepository<Test, String> {
}
