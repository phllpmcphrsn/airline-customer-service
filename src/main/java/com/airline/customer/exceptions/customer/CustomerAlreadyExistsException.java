package com.airline.customer.exceptions.customer;

public class CustomerAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  
    public CustomerAlreadyExistsException(String customerId) {
        super("Customer already exists within the system: " + customerId);
    }
}
