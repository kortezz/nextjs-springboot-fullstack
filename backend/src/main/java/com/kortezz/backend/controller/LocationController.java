package com.kortezz.backend.controller;

import com.kortezz.backend.entity.Location;
import com.kortezz.backend.exception.ResourceNotFoundException;
import com.kortezz.backend.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"}, maxAge = 3600)
@RestController
@RequestMapping("/api/locations")
@AllArgsConstructor
public class LocationController {

    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Long id) {
        return locationService.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Location not found for this id :: " + id));
    }

    @PostMapping
    public Location saveLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable Long id) {
        locationService.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Location not found for this id :: " + id));
        locationService.deleteLocation(id);
    }
}
