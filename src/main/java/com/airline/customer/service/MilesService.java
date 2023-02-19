package com.airline.customer.service;

import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;

public interface MilesService {
    public Miles getMiles(Customer customer);
    public Miles createMiles(Customer customer);
    public Miles updateMiles(Customer customer);
}