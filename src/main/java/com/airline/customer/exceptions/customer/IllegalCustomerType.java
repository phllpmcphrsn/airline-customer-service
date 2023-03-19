package com.airline.customer.exceptions.customer;

import com.airline.customer.data.enums.CustomerType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IllegalCustomerType extends IllegalArgumentException {
    String message;
    CustomerType customerType;
}
