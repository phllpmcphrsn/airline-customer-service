package com.airline.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.airline.customer.data.enums.CustomerType;
import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;
import com.airline.customer.exceptions.CustomerNotFoundException;
import com.airline.customer.repository.CustomerRepository;
import com.airline.customer.repository.MilesRepository;
import com.airline.customer.service.CustomerService;
import com.airline.customer.service.MilesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    
    // Spring will inject using constructor
    // no need for @Autowired - helps with testing since
    // we won't have to worry about Spring loading
    private final CustomerRepository customerRepository;
    private final MilesRepository milesRepository;
    private MilesService milesService;

    @Override
    public Customer createCustomer(Customer customer) {
        // could use try-catch to check if customer exists 
        // before performing the below
        if(customer.getCustomerType() == CustomerType.MEMBER) {
            milesService.createMiles(customer);
            log.info(null);
        }
        return save(customer);        
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = findById(customer.getId());
        return save(customerOptional.get());        
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Optional<Customer> customer = findById(id);
        List<Miles> miles = milesRepository.findByCustomer(customer.get());
        milesRepository.deleteAll(miles);
        customerRepository.delete(customer.get());
    }
    

    Customer save(Customer customer) {
        return customerRepository.save(customer);
    }   


    public Optional<Customer> findById(String id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
      }
      
}
