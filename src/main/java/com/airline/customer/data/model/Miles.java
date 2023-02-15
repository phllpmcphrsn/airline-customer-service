package com.airline.customer.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Miles {

    @Id
    private String id;

    private String customerId;

    private String flightId;

    private int milesEarned;

    private Date redemptionDate;
}