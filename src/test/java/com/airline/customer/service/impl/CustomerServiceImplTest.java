package com.airline.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.airline.customer.data.enums.CustomerType;
import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;
import com.airline.customer.exceptions.CustomerNotFoundException;
import com.airline.customer.repository.CustomerRepository;
import com.airline.customer.service.MilesService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    // With ExtendWith we can simply use the @Mock annotation
    // private MilesRepository milesRepository = Mockito.mock(MilesRepository.class);
    // private CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

    @Mock
    private MilesService milesService;

    @Mock
    private CustomerRepository customerRepository;

    private CustomerServiceImpl customerServiceImpl;

    private String address = "1234 ome address, here, andthere 11111";
    private String email = "test@email.com";
    private String phoneNumber = "123-123-1234";

    @BeforeEach
    void initService() {
        customerServiceImpl = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testCreateCustomer() {
        Customer guest = new Customer("1", "test", "test", address, phoneNumber, email, CustomerType.GUEST);
        Miles miles = new Miles();
        when(milesService.createMiles(any(Customer.class))).thenReturn(List.of(miles));
        Customer result = customerServiceImpl.createCustomer(guest);
        assertNotNull(result);
        assertEquals(guest.getFirstName(), result.getFirstName());
        assertEquals(guest.getLastName(), result.getLastName());
        assertEquals(guest.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(guest.getFirstName(), result.getFirstName());
        assertEquals(guest.getLastName(), result.getLastName());
        assertEquals(CustomerType.GUEST, result.getCustomerType());
        assertNull(result.getMiles());
        
        // Customer member = new Customer("1", "test", "test", address, phoneNumber, email, CustomerType.MEMBER);
    }

    @Test
    void testDeleteCustomer_SuccessWithCustomers() {
        Customer customer = new Customer("1", "test", "tester", "1234 ome address, here, andthere 11111", "123-123-1234", "test@email.com", CustomerType.MEMBER);
        Optional<Customer> optional = Optional.of(customer);
        
        when(customerRepository.findById(anyString())).thenReturn(optional);

        List<Miles> miles = List.of(new Miles());
        when(milesRepository.findByCustomer(any(Customer.class))).thenReturn(miles);
        assertDoesNotThrow(() -> customerServiceImpl.deleteCustomer(customer.getId()));
    }

    @Test
    void testGetAllCustomers_SuccessWithCustomers() {
        List<Customer> customers = List.of(new Customer("1", "test", "test", address, phoneNumber, email, CustomerType.GUEST));
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> actual = customerServiceImpl.getAllCustomers();

        assertEquals(customers, actual);
    }

    @Test
    void testGetAllCustomers_SuccessWithoutCustomers() {
        List<Customer> actual = customerServiceImpl.getAllCustomers();
        assertEquals(actual.size(), 0);
    }

    @Test
    void testUpdateCustomer_Success() {
        Customer customer = new Customer("1", "test", "tester", "1234 ome address, here, andthere 11111", "123-123-1234", "test@email.com", CustomerType.MEMBER);
        Optional<Customer> optional = Optional.of(customer);
        
        when(customerRepository.findById(anyString())).thenReturn(optional);
        
        Customer updatedCustomer = new Customer("1", "new", "name", "1234 ome address, here, andthere 11111", "123-123-2345", "test@email.com", CustomerType.MEMBER);
            
        Customer result = customerServiceImpl.updateCustomer(updatedCustomer);
        
        // ensure updates occurred
        assertEquals(updatedCustomer.getFirstName(), result.getFirstName());
        assertEquals(updatedCustomer.getLastName(), result.getLastName());
        assertEquals(updatedCustomer.getPhoneNumber(), result.getPhoneNumber());
        
        // ensure old customer data does not equal updated data
        assertNotEquals(customer.getFirstName(), result.getFirstName());
        assertNotEquals(customer.getLastName(), result.getLastName());
        assertNotEquals(customer.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    void testCustomerNotFound() throws CustomerNotFoundException {
        assertThrows(CustomerNotFoundException.class, () -> customerServiceImpl.updateCustomer(new Customer()));
        assertThrows(CustomerNotFoundException.class, () -> customerServiceImpl.deleteCustomer(""));
    }
}
