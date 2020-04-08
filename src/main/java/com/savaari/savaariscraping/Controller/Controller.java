package com.savaari.savaariscraping.Controller;

import com.savaari.savaariscraping.Entity.Entity;
import com.savaari.savaariscraping.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
@CrossOrigin("*")
public class Controller {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<Entity> getCities() {
        return cityService.getCities();
    }

    @PostMapping
    public List<Entity> createCities(@RequestBody List<Entity> cities) {
        return cityService.createCities(cities);
    }

    @PutMapping
    public Optional<Entity> updateCities(Integer cityId, @RequestBody Entity cities) {
        return cityService.updateCities(cityId, cities);
    }
}
