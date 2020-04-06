package com.savaari.savaariscraping.Service;

import com.savaari.savaariscraping.DAO.DAO;
import com.savaari.savaariscraping.Entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private DAO cityDAO;

    public List<Entity> getCities() {
        return cityDAO.getCities();
    }

    public List<Entity> createCities(List<Entity> city) {
        return cityDAO.createCities(city);
    }

}