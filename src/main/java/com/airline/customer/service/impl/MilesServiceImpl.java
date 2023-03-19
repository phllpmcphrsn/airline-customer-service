package com.airline.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;
import com.airline.customer.exceptions.miles.MilesNotFoundException;
import com.airline.customer.repository.MilesRepository;
import com.airline.customer.service.MilesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MilesServiceImpl implements MilesService {
    private final MilesRepository milesRepository;
    
    @Override
    public List<Miles> createMiles(Customer customer) {
        // could use try-catch to check if customer exists 
        // before performing the below

        // if(customer.getCustomerType() != CustomerType.MEMBER) {
        //     throw new IllegalCustomerType("Only members can create miles. Customer type: ", customer.getCustomerType());
        //     log.error("Only members can create miles. Customer type: {}", customer.getCustomerType());
        // } 

        return milesRepository.insert(customer.getMiles()); 
    }

    @Override
    public Miles getMiles(String milesId) {
        Optional<Miles> miles = milesRepository.findById(milesId);
        if(!miles.isPresent()) {
            throw new MilesNotFoundException(milesId);
        }
        return miles.get();
    }

    @Override
    public List<Miles> getMilesByCustomerId(String customerId) {
        return milesRepository.findByCustomerId(customerId);
    }

    @Override
    public Miles updateMiles(Miles miles) {
        return milesRepository.save(miles);
    }

    @Override
    public void deleteMiles(Miles miles) {
        milesRepository.delete(miles);
    }

    @Override
    public void deleteAllMiles(String customerId) {
        milesRepository.deleteByCustomerId(customerId);
    }
    
}