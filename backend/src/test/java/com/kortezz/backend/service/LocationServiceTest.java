package com.kortezz.backend.service;

import com.kortezz.backend.entity.Location;
import com.kortezz.backend.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @InjectMocks
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    @Mock
    MailService mailSender;

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(1L,"city","district");
    }

    @Test
    void calledFindAll_returnLocationList() {
        when(locationRepository.findAll()).thenReturn(Collections.singletonList(location));

        List<Location> locations = locationService.findAll();

        assertEquals(1, locations.size());
        assertEquals(location.getCity(), locations.get(0).getCity());
        assertEquals(location.getDistrict(), locations.get(0).getDistrict());
    }

    @Test
    void calledSaveLocation_returnSavedLocation() {
        Location locationBeforeSaved = new Location(null,"city","district");
        when(locationRepository.save(locationBeforeSaved)).thenReturn(location);

        Location result = locationService.saveLocation(locationBeforeSaved);

        assertEquals(locationBeforeSaved.getCity(), result.getCity());
        assertEquals(locationBeforeSaved.getDistrict(), result.getDistrict());
        verify(mailSender, times(1)).sendMail(any());
    }

    @Test
    void calledDeleteLocation_calledFunctionInRepository() {

        locationService.deleteLocation(1L);

        verify(locationRepository, times(1)).deleteById(1L);
    }

    @Test
    void calledFindById_returnAvailableLocation() {
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));

        Optional<Location> result = locationService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(location.getCity(), result.get().getCity());
        assertEquals(location.getDistrict(), result.get().getDistrict());
    }
}
