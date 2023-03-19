package com.airline.customer.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseMessage {
    private HttpStatus code;
    private Date timestamp;
    private String message;

    // this'll hold some basic info about the request
    private String description;
}
