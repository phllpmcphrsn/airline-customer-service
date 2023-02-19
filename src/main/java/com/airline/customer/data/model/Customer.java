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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "customers")
public class Customer {
    @Id
    private String id;
     
    @NotBlank 
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;
    @NotBlank
    @Email(message = "Email is not valid")
    private String email;

    // Store Enums as Strings in the database
    @NotBlank
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

    // Could use lombok.NonNull on these fields, but I find it awkward to use both
    // @NonNull and @NotBlank in the sense of validation
    public Customer(String id, String firstName, String lastName, String address, String phoneNumber, String email, CustomerType type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerType = type;
    }
}
