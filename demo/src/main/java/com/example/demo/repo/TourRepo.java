package com.example.demo.repo;

import com.example.demo.domain.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface TourRepo extends MongoRepository<Tour, String> {
   Tour findByName(String name);


}
