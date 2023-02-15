package com.airline.customer.service;

import java.util.List;

import com.airline.customer.data.model.Customer;
import com.airline.customer.exceptions.CustomerNotFoundException;

public interface CustomerService {
	public void createCustomer(Customer customer); 
	public Customer getCustomerByFirstName(String firstname); 
	public List<Customer> getAllCustomers();
	public void updateCustomer(Customer customer); 
	public void deleteCustomer(String id) throws CustomerNotFoundException; 
}