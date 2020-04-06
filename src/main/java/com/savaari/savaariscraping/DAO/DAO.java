package com.savaari.savaariscraping.DAO;

import com.savaari.savaariscraping.Entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DAO {
    @Autowired
    private DAOMongo repository;

    public List<Entity> getCities() {
        return repository.findAll();
    }

    public List<Entity> createCities(List<Entity> cities) {
        return repository.insert(cities);
    }
}
