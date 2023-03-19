package com.airline.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.airline.customer.data.enums.CustomerType;
import com.airline.customer.data.model.Customer;
import com.airline.customer.exceptions.customer.CustomerNotFoundException;
import com.airline.customer.repository.CustomerRepository;
import com.airline.customer.service.CustomerService;
import com.airline.customer.service.MilesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  
@Service
public class CustomerServiceImpl implements CustomerService {
    
    // Spring will inject using constructor
    // no need for @Autowired - helps with testing since
    // we won't have to worry about Spring loading
    private final CustomerRepository customerRepository;
    private final MilesService milesService;

    @Override
    public Customer createCustomer(Customer customer) {
        // could use try-catch to check if customer exists 
        // before performing the below
        
        // what if a former GUEST becomes a MEMBER?
        
        // may have to do this in the Controller actually
        // CustomerService won't have the info needed for a 
        // Miles object
        if(customer.getCustomerType() == CustomerType.MEMBER) {
            milesService.createMiles(customer);
        }
        return customerRepository.insert(customer);        
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
        Customer updatedCustomer = findById(customer.getId()).get();
        return customerRepository.save(updatedCustomer);        
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Customer customer = findById(id).get();
        if(customer.getCustomerType() == CustomerType.MEMBER) {
            milesService.deleteAllMiles(customer.getId());
        }
        customerRepository.delete(customer);
    }

    public Optional<Customer> findById(String id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }
      
}
