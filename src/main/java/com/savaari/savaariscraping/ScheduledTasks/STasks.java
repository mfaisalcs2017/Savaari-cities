package com.savaari.savaariscraping.ScheduledTasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.savaari.savaariscraping.DAO.DAOMongo;
import com.savaari.savaariscraping.DAO.DestinationCityDAOMongo;
import com.savaari.savaariscraping.Entity.CityEntity;
import com.savaari.savaariscraping.Entity.DestinationCityEntity;
import com.savaari.savaariscraping.Entity.DestinationEntity;
import com.savaari.savaariscraping.Entity.Entity;
import com.savaari.savaariscraping.Service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component
public class STasks {
    MongoClient client = new MongoClient("127.0.0.1", 27017);
    MongoDatabase db = client.getDatabase("test");
    boolean sourceCityCollectionExist = db.listCollectionNames().into(new ArrayList<>()).contains("cities");
    boolean destCityCollectionExist = db.listCollectionNames().into(new ArrayList<>()).contains("destination-cities");
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public CityService cityService;
    @Autowired
    public DAOMongo repository;
    @Autowired
    DestinationCityDAOMongo destCityRepo;
    private static final Logger log = LoggerFactory.getLogger(STasks.class);


    @EventListener(ApplicationReadyEvent.class)
    public void createDataInDbAfterStartup() {
        if (!destCityCollectionExist) {
            try {
                DestinationCityEntity data2 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/destination-cities?tripType=outstation&subTripType=roundTrip&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1ODYxOTkwMzgsImp0aSI6Iis0VWhaU2NnU0ZvQ2w0MUNNTVwvOWU3SUg3ZkN4QXgrR2NaUHVrd1h2Nlwvbz0iLCJpc3MiOiJzYXZhYXJpIiwibmJmIjoxNTg2MTk5MDM4LCJleHAiOjE1ODY4MDM4MzgsImRhdGEiOnsiYXBpS2V5IjoiNTc2YTY3ODNlYTU0ZiIsImFwcElkIjoiMzkxYzU0ZWNjNjgzODJmMGMyNzE4MDU3MzgyMjE0ZTUifX0.8wrgmbdqEG2I4cNtk3eRE5C-gtjkbaPPPdlHsEReIhK2XTS3kAB2EF5rf0ScVFW6C7my4UiQ0soRHduN9N6Efg&sourceCity=377"), DestinationCityEntity.class);
                cityService.createDestinationCity(data2.getData());
                System.out.println("destination cities collection created--------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!sourceCityCollectionExist) {
            try {
                CityEntity data1 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/source-cities?tripType=outstation&subTripType=roundTrip&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1ODU5NDQzMjQsImp0aSI6IjU0UFBFWnhtbFF3eGRQS0JVV1NVMlFqUDB4bThhWjREekdTZkt6bVVVWWM9IiwiaXNzIjoic2F2YWFyaSIsIm5iZiI6MTU4NTk0NDMyNCwiZXhwIjoxNTg2NTQ5MTI0LCJkYXRhIjp7ImFwaUtleSI6IjU3NmE2NzgzZWE1NGYiLCJhcHBJZCI6IjM5MWM1NGVjYzY4MzgyZjBjMjcxODA1NzM4MjIxNGU1In19.l1Mk1UsxepZGlTm8XAvXictq0XPkVgg6cX6GiFKdyND00YCuW6LisIGwKJC2UDD1yBYbHtiRHQFdLN3jOxE1Hw"), CityEntity.class);
                cityService.createCities(data1.getData());
                System.out.println("source cities collection created--------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * MON")
    public void updateDbDataFromApi() {
        try {
            CityEntity data1 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/source-cities?tripType=outstation&subTripType=roundTrip&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1ODU5NDQzMjQsImp0aSI6IjU0UFBFWnhtbFF3eGRQS0JVV1NVMlFqUDB4bThhWjREekdTZkt6bVVVWWM9IiwiaXNzIjoic2F2YWFyaSIsIm5iZiI6MTU4NTk0NDMyNCwiZXhwIjoxNTg2NTQ5MTI0LCJkYXRhIjp7ImFwaUtleSI6IjU3NmE2NzgzZWE1NGYiLCJhcHBJZCI6IjM5MWM1NGVjYzY4MzgyZjBjMjcxODA1NzM4MjIxNGU1In19.l1Mk1UsxepZGlTm8XAvXictq0XPkVgg6cX6GiFKdyND00YCuW6LisIGwKJC2UDD1yBYbHtiRHQFdLN3jOxE1Hw"), CityEntity.class);
            List<Entity> dataList = data1.getData();
            for (Entity entity_obj : dataList) {
                Entity dbObj = repository.findByCityId(entity_obj.getCityId());
                if (dbObj != null) {
                    System.out.println("updating source cities--------------");
                    cityService.updateCities(entity_obj.getCityId(), entity_obj);
                } else {
                    System.out.println("inserting source cities--------------");
                    repository.insert(entity_obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DestinationCityEntity data2 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/destination-cities?tripType=outstation&subTripType=roundTrip&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1ODYxOTkwMzgsImp0aSI6Iis0VWhaU2NnU0ZvQ2w0MUNNTVwvOWU3SUg3ZkN4QXgrR2NaUHVrd1h2Nlwvbz0iLCJpc3MiOiJzYXZhYXJpIiwibmJmIjoxNTg2MTk5MDM4LCJleHAiOjE1ODY4MDM4MzgsImRhdGEiOnsiYXBpS2V5IjoiNTc2YTY3ODNlYTU0ZiIsImFwcElkIjoiMzkxYzU0ZWNjNjgzODJmMGMyNzE4MDU3MzgyMjE0ZTUifX0.8wrgmbdqEG2I4cNtk3eRE5C-gtjkbaPPPdlHsEReIhK2XTS3kAB2EF5rf0ScVFW6C7my4UiQ0soRHduN9N6Efg&sourceCity=377"), DestinationCityEntity.class);
            List<DestinationEntity> dataList = data2.getData();
            for (DestinationEntity entity_obj : dataList) {
                DestinationEntity dbObj = destCityRepo.findByCityId(entity_obj.getCityId());
                if (dbObj != null) {
                    System.out.println("updating destination cities--------------");
                    cityService.updateDestinationCities(entity_obj.getCityId(), entity_obj);
                } else {
                    System.out.println("inserting destination cities--------------");
                    destCityRepo.insert(entity_obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

