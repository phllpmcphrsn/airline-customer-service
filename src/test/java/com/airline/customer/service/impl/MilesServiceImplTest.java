package com.airline.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.airline.customer.data.model.Customer;
import com.airline.customer.data.model.Miles;
import com.airline.customer.repository.MilesRepository;
import com.airline.customer.service.MilesService;

@SpringBootTest
public class MilesServiceImplTest {

    private MilesRepository milesRepository;
    private MilesService milesService;

    @BeforeEach
    void setUp() {
        milesRepository = mock(MilesRepository.class);
        milesService = new MilesServiceImpl(milesRepository);
    }

    @Test
    @DisplayName("Test createMiles method")
    void testCreateMiles() {
        Customer customer = new Customer();
        customer.setId("123");
        customer.setMiles(new ArrayList<>());

        List<Miles> milesList = new ArrayList<>();
        milesList.add(new Miles());

        when(milesRepository.insert(customer.getMiles())).thenReturn(milesList);

        List<Miles> result = milesService.createMiles(customer);

        assertEquals(milesList, result);
    }

    @Test
    @DisplayName("Test updateMiles method")
    void testUpdateMiles() {
        Miles miles = new Miles();
        miles.setId("456");
        miles.setCustomerId("123");

        when(milesRepository.save(miles)).thenReturn(miles);

        Miles result = milesService.updateMiles(miles);

        assertEquals(miles, result);
    }

    @Test
    @DisplayName("Test deleteMiles method")
    void testDeleteMiles() {
        Miles miles = new Miles();
        miles.setId("456");
        miles.setCustomerId("123");

        milesService.deleteMiles(miles);

        // verify that the delete method on the repository was called with the correct argument
        verify(milesRepository).delete(miles);
    }

    @Test
    @DisplayName("Test deleteAllMiles method")
    void testDeleteAllMiles() {
        String customerId = "123";

        milesService.deleteAllMiles(customerId);

        // verify that the deleteByCustomerId method on the repository was called with the correct argument
        verify(milesRepository).deleteByCustomerId(customerId);
    }
}
