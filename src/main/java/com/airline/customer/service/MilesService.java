package com.airline.customer.service;

import java.util.List;

import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;

public interface MilesService {
    public List<Miles> getMilesByCustomerId(String customerId);
    public Miles getMiles(String milesId);
    public List<Miles> createMiles(Customer customer);
    public Miles updateMiles(Miles miles);
    public void deleteMiles(Miles miles);
    public void deleteAllMiles(String customerId);
}