package com.airline.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.customer.data.model.Miles;
import com.airline.customer.exceptions.customer.CustomerNotFoundException;
import com.airline.customer.exceptions.miles.MilesNotFoundException;
import com.airline.customer.service.MilesService;

@RestController
@RequestMapping("/miles")
public class MilesController {
    
    @Autowired
    MilesService milesService;

    @GetMapping("/{id}")
    public ResponseEntity<Miles> getMiles(@PathVariable("id") String id) {
        try {
            Miles miles = milesService.getMiles(id);
            return ResponseEntity.ok(miles);
        } catch (MilesNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Miles>> getMilesByCustomerId(@PathVariable("customerId") String customerId) {
        try {
            List<Miles> milesList = milesService.getMilesByCustomerId(customerId);
            return ResponseEntity.ok(milesList);
        } catch (MilesNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
