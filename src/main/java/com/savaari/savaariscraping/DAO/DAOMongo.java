package com.savaari.savaariscraping.DAO;

import com.savaari.savaariscraping.Entity.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DAOMongo extends MongoRepository<Entity, Integer> {
    public Entity findByCityId(Integer id);
}
