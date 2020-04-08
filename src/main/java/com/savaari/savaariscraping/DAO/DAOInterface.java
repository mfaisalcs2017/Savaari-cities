package com.savaari.savaariscraping.DAO;

import com.savaari.savaariscraping.Entity.Entity;

import java.util.List;
import java.util.Optional;

public interface DAOInterface {
    List<Entity> getCities();

    List<Entity> createCities(List<Entity> city);

    Optional<Entity> updateCities(Integer cityId, Entity city);
}
