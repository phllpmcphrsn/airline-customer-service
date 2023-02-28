package com.airline.customer.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  
    public CustomerNotFoundException(String customerId) {
      super("Customer not found with id: " + customerId);
    }
  }
  
