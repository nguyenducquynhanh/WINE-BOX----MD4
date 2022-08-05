package com.cg.controller.rest;

import com.cg.model.Capacity;
import com.cg.model.Category;
import com.cg.service.capacity.ICapacityService;
import com.cg.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/capacities")
public class CapacityRestController {

    @Autowired
    private ICapacityService capacityService;

    @GetMapping
    public ResponseEntity<?> showListCapacity() {
        Iterable<Capacity> capacities = capacityService.findAll();

        if(capacities == null) {
            return new ResponseEntity<>("List is empty!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(capacities, HttpStatus.OK);
    }
}