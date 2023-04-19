package com.kortezz.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kortezz.backend.entity.Location;
import com.kortezz.backend.service.LocationService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @MockBean
    private LocationService locationService;

    @Autowired
    MockMvc mockMvc;

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(1L,"city","district");
    }

    @Test
    void calledGetAllLocation_returnAllLocations() throws Exception {
        when(locationService.findAll()).thenReturn(Collections.singletonList(location));

        mockMvc.perform(get("/api/locations"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(1)))
            .andExpect(jsonPath("$[0].city", Matchers.is("city")))
            .andExpect(jsonPath("$[0].district", Matchers.is("district")));
    }

    @Test
    void calledfindById_returnAvailableLocation() throws Exception {
        when(locationService.findById(1L)).thenReturn(Optional.of(location));

        mockMvc.perform(get("/api/locations/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id", Matchers.is(1)))
            .andExpect(jsonPath("city", Matchers.is("city")))
            .andExpect(jsonPath("district", Matchers.is("district")));
    }

    @Test
    void calledfindById_unavailableLocation_throwException() throws Exception {
        when(locationService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/locations/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    void calledSaveLocation_returnSavedLocation() throws Exception {
        when(locationService.saveLocation(any())).thenReturn(location);

        mockMvc.perform(post("/api/locations")
                .content(asJsonString(new Location(null, "city", "district")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("id", Matchers.is(1)))
            .andExpect(jsonPath("city", Matchers.is("city")))
            .andExpect(jsonPath("district", Matchers.is("district")));
    }

    @Test
    void calledDeleteMapping_availableLocationDelete() throws Exception {
        when(locationService.findById(1L)).thenReturn(Optional.of(location));

        mockMvc.perform(delete("/api/locations/1"))
            .andExpect(status().isOk());
        verify(locationService, times(1)).deleteLocation(1L);
    }

    @Test
    void calledDeleteMapping_UnavailableLocation_throwException() throws Exception {
        mockMvc.perform(delete("/api/locations/1"))
            .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
