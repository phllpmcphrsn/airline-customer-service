package com.airline.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.airline.customer.data.model.Miles;

@Repository
public interface MilesRepository extends MongoRepository<Miles, String> {
    Optional<Miles> findById(String id);

    List<Miles> findByCustomerId(String customerId);

    void delete(Miles miles);
    
    // probably need to create a custom query for this
    void deleteByCustomerId(String customerId);
}

