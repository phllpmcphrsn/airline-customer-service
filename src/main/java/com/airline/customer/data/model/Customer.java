/**************************************************
 * Represents a customer.
 * Uses an Enum to determine the type of customer.
 */
package com.airline.customer.data.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.EnumType;
import com.airline.customer.data.enums.CustomerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "customers")
public class Customer {
    @Id
    private String id;
     
    @NonNull 
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String address;
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;

    // Store Enums as Strings in the database
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    // We'd like to track miles on a per flight-basis for
    // better history/tracking
    @OneToMany(mappedBy = "customer")
    private List<Miles> miles;

    private String passportNumber;
    private List<Reservation> reservations;
    private Payment payment;
    private List<Feedback> feedbacks;
}
