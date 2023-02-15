package com.airline.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;
import com.airline.customer.exceptions.CustomerNotFoundException;
import com.airline.customer.repository.CustomerRepository;
import com.airline.customer.repository.MilesRepository;
import com.airline.customer.service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MilesRepository milesRepository;

    @Override
    public void createCustomer(Customer customer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Customer getCustomerByFirstName(String firstname) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        customerRepository.save(customerOptional.get());        
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        List<Miles> miles = milesRepository.findByCustomer(customer.get());
        milesRepository.deleteAll(miles);
        customerRepository.delete(customer.get());
    }
    

    public void save(Customer customer) {
        customerRepository.save(customer);
    }   


    public Optional<Customer> findById(String id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
      }
      
}
