package com.savaari.savaariscraping.DAO;

import com.savaari.savaariscraping.Entity.DestinationEntity;

import java.util.List;
import java.util.Optional;

public interface DestinationCityDAOInterface {
    List<DestinationEntity> getDestinationCities();

    List<DestinationEntity> createDestinationCity(List<DestinationEntity> city);

    Optional<DestinationEntity> updateDestinationCities(Integer cityId, DestinationEntity destinationCity);
}
