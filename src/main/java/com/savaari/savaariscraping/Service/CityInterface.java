package com.savaari.savaariscraping.Service;

import com.savaari.savaariscraping.Entity.DestinationEntity;
import com.savaari.savaariscraping.Entity.Entity;

import java.util.List;
import java.util.Optional;

public interface CityInterface {
    List<Entity> getCities();

    List<Entity> createCities(List<Entity> city);

    List<DestinationEntity> getDestinationCities();

    List<DestinationEntity> createDestinationCity(List<DestinationEntity> city);

    Optional<Entity> updateCities(Integer cityId, Entity city);

    Optional<DestinationEntity> updateDestinationCities(Integer cityId, DestinationEntity city);
}


