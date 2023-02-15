package com.airline.customer.data.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private String flightNumber;
    private Date departureDate;
    private Date arrivalDate;
    private double ticketPrice;
}

