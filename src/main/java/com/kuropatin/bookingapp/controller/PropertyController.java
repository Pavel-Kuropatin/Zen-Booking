package com.kuropatin.bookingapp.controller;

import com.kuropatin.bookingapp.model.Property;
import com.kuropatin.bookingapp.model.dto.PropertyDto;
import com.kuropatin.bookingapp.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService service;

    @GetMapping
    public ResponseEntity<List<Property>> getAll(@PathVariable final Long userId) {
        return new ResponseEntity<>(service.getAllPropertyOfUser(userId), HttpStatus.OK);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getById(@PathVariable final Long propertyId) {
        return new ResponseEntity<>(service.getPropertyById(propertyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Property> create(@PathVariable final Long userId, @RequestBody final PropertyDto propertyDto) {
        Property propertyToSave = service.createProperty(userId, propertyDto);
        return new ResponseEntity<>(propertyToSave, HttpStatus.CREATED);
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<Property> update(@PathVariable final Long propertyId, @RequestBody final PropertyDto propertyDto) {
        return new ResponseEntity<>(service.updateProperty(propertyId, propertyDto), HttpStatus.OK);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteById(@PathVariable final Long propertyId) {
        return new ResponseEntity<>(service.softDeleteProperty(propertyId), HttpStatus.OK);
    }
}