package com.savaari.savaariscraping.ScheduledTasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.savaari.savaariscraping.DAO.DAOMongo;
import com.savaari.savaariscraping.DAO.DestinationCityDAOMongo;
import com.savaari.savaariscraping.Entity.*;
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
        TokenRequestEntity tokenData = null;
        try {
         tokenData = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/auth/token?apiKey=576a6783ea54f&appId=391c54ecc68382f0c2718057382214e5"), TokenRequestEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!destCityCollectionExist) {
            try {
                System.out.println(tokenData.getData().token);
                DestinationCityEntity data2 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/destination-cities?tripType=outstation&subTripType=roundTrip&token="+tokenData.getData().token+"&sourceCity=377"), DestinationCityEntity.class);
                cityService.createDestinationCity(data2.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!sourceCityCollectionExist) {
            try {
                CityEntity data1 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/source-cities?tripType=outstation&subTripType=roundTrip&token="+tokenData.getData().token), CityEntity.class);
                cityService.createCities(data1.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * MON")
    public void updateDbDataFromApi() {
        TokenRequestEntity tokenData = null;
        try {
            tokenData = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/auth/token?apiKey=576a6783ea54f&appId=391c54ecc68382f0c2718057382214e5"), TokenRequestEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            CityEntity data1 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/source-cities?tripType=outstation&subTripType=roundTrip&token="+tokenData.getData().token), CityEntity.class);
            List<Entity> dataList = data1.getData();
            for (Entity entity_obj : dataList) {
                Entity dbObj = repository.findByCityId(entity_obj.getCityId());
                if (dbObj != null) {
                    cityService.updateCities(entity_obj.getCityId(), entity_obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DestinationCityEntity data2 = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/destination-cities?tripType=outstation&subTripType=roundTrip&token="+tokenData.getData().token+"&sourceCity=377"), DestinationCityEntity.class);
            List<DestinationEntity> dataList = data2.getData();
            for (DestinationEntity entity_obj : dataList) {
                DestinationEntity dbObj = destCityRepo.findByCityId(entity_obj.getCityId());
                if (dbObj != null) {
                    cityService.updateDestinationCities(entity_obj.getCityId(), entity_obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

