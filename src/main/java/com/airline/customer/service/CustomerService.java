package com.airline.customer.service;

import java.util.List;
import java.util.Optional;

import com.airline.customer.data.model.Customer;
import com.airline.customer.exceptions.customer.CustomerNotFoundException;

public interface CustomerService {
	public Customer createCustomer(Customer customer); 
	public Optional<Customer> findById(String id);
	public List<Customer> getAllCustomers();
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException; 
	public void deleteCustomer(String id) throws CustomerNotFoundException; 
}