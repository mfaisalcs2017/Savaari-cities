package com.savaari.savaariscraping.Controller;

import com.savaari.savaariscraping.Entity.DestinationEntity;
import com.savaari.savaariscraping.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destination-cities")
@CrossOrigin("*")
public class DestinationController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<DestinationEntity> getDestinationCities() {
        return cityService.getDestinationCities();
    }

    @PostMapping()
    public List<DestinationEntity> createDestinationCities(@RequestBody List<DestinationEntity> cities) {
        return cityService.createDestinationCity(cities);
    }
    @PutMapping()
    public Optional<DestinationEntity> updateDestinationCities(Integer cityId, @RequestBody DestinationEntity city) {
        return cityService.updateDestinationCities(cityId, city);
    }


}
