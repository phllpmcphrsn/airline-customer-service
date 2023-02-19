package com.airline.customer.repository;

import com.airline.customer.data.model.Customer;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Optional<Customer> findById(String id);
    public Customer save(Customer customer);
    public void deleteById(String id);
    public void delete(Customer customer);


}
