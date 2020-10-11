package com.example.demo.repo;

import com.example.demo.domain.Tour;
import com.example.demo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByName(String name);
}
