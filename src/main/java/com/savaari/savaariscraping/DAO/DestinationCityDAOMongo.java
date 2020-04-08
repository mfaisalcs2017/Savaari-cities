package com.savaari.savaariscraping.DAO;

import com.savaari.savaariscraping.Entity.DestinationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationCityDAOMongo extends MongoRepository<DestinationEntity, Integer> {
    public DestinationEntity findByCityId(Integer id);
}
