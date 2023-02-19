package com.airline.customer.service;

import java.util.List;

import com.airline.customer.data.model.Customer;
import com.airline.customer.exceptions.CustomerNotFoundException;

public interface CustomerService {
	public Customer createCustomer(Customer customer); 
	public List<Customer> getAllCustomers();
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException; 
	public void deleteCustomer(String id) throws CustomerNotFoundException; 
}