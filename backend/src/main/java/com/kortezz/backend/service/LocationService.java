package com.kortezz.backend.service;

import com.kortezz.backend.entity.Location;
import com.kortezz.backend.repository.LocationRepository;
import com.kortezz.backend.util.MailUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private MailService mailService;


    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location saveLocation(Location location) {
        Location allByCityAndDistrict = locationRepository.findByCityAndDistrict(location.getCity(), location.getDistrict());
        if(allByCityAndDistrict != null) {
            return allByCityAndDistrict;
        }
        String upperCase = Optional.ofNullable(location.getCity()).orElse("").toUpperCase();
        location.setCity(upperCase);
        Location save = locationRepository.save(location);
        mailService.sendMail(MailUtil.generateMail(location));
        return save;
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }
}
