package com.savaari.savaariscraping.Service;

import com.savaari.savaariscraping.DAO.DAO;
import com.savaari.savaariscraping.DAO.DestinationCityDAO;
import com.savaari.savaariscraping.Entity.DestinationEntity;
import com.savaari.savaariscraping.Entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements CityInterface {
    @Autowired
    private DAO cityDAO;
    @Autowired
    private DestinationCityDAO destCityDAO;

    @Override
    public List<Entity> getCities() {
        return cityDAO.getCities();
    }

    @Override
    public List<Entity> createCities(List<Entity> city) {
        return cityDAO.createCities(city);
    }

    @Override
    public Optional<Entity> updateCities(Integer cityId, Entity city) {
        return cityDAO.updateCities(cityId, city);
    }

    @Override
    public List<DestinationEntity> getDestinationCities() {
        return destCityDAO.getDestinationCities();
    }

    @Override
    public List<DestinationEntity> createDestinationCity(List<DestinationEntity> city) {
        return destCityDAO.createDestinationCity(city);
    }

    @Override
    public Optional<DestinationEntity> updateDestinationCities(Integer cityId, DestinationEntity city) {
        return destCityDAO.updateDestinationCities(cityId, city);
    }

}