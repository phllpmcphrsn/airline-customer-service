package com.airline.customer.exceptions.miles;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MilesNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  
    public MilesNotFoundException(String milesId) {
      super("Miles could not be found for the following id: " + milesId);
    }
}
  
