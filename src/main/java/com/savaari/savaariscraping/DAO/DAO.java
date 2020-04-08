package com.savaari.savaariscraping.DAO;

import com.savaari.savaariscraping.Entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DAO implements DAOInterface {
    @Autowired
    private DAOMongo repository;

    @Override
    public List<Entity> getCities() {
        return repository.findAll();
    }

    @Override
    public List<Entity> createCities(List<Entity> cities) {
        return repository.insert(cities);
    }

    @Override
    public Optional<Entity> updateCities(Integer cityId, Entity city) {
        Optional<Entity> cities = repository.findById(cityId);
        cities.ifPresent(sCity -> sCity.setCityId(city.getCityId()));
        cities.ifPresent(sCity -> sCity.setAlias(city.getAlias()));
        cities.ifPresent(sCity -> sCity.setCityName(city.getCityName()));
        cities.ifPresent(sCity -> repository.save(sCity));
        return cities;
    }
}
