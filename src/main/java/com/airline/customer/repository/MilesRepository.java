package com.airline.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;

@Repository
public interface MilesRepository extends MongoRepository<Miles, String> {
    List<Miles> findByCustomer(Optional<Customer> customer);

    void deleteAll(List<Miles> miles);

    List<Miles> findByCustomer(Customer customer);
}
