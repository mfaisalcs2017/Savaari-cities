package com.savaari.savaariscraping.DAO;

import com.savaari.savaariscraping.Entity.DestinationEntity;
import com.savaari.savaariscraping.Entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DestinationCityDAO implements DestinationCityDAOInterface {
    @Autowired
    private DestinationCityDAOMongo repository;

    @Override
    public List<DestinationEntity> getDestinationCities() {
        return repository.findAll();
    }

    @Override
    public List<DestinationEntity> createDestinationCity(List<DestinationEntity> city) {
        return repository.insert(city);
    }

    @Override
    public Optional<DestinationEntity> updateDestinationCities(Integer cityId, DestinationEntity destinationCity) {
        Optional<DestinationEntity> destCities = repository.findById(cityId);
        destCities.ifPresent(destCity -> destCity.setCityId(destinationCity.getCityId()));
        destCities.ifPresent(destCity -> destCity.setAlias(destinationCity.getAlias()));
        destCities.ifPresent(destCity -> destCity.setCityName(destinationCity.getCityName()));
        destCities.ifPresent(destCity -> destCity.setSource_city_id(destinationCity.getSource_city_id()));
        destCities.ifPresent(destCity -> repository.save(destCity));
        return destCities;
    }
}
