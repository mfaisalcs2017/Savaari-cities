package com.savaari.savaariscraping.ScheduledTasks;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savaari.savaariscraping.Entity.CityEntity;
import com.savaari.savaariscraping.Service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;


@Component
public class STasks {
    @Autowired
    public CityService cityService;
    private static final Logger log = LoggerFactory.getLogger(STasks.class);
    @Scheduled(cron = "0 0 12 */7 * ?")
    public void checkApi() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CityEntity data = mapper.readValue(new URL("https://api.savaari.com/partner_api/public/source-cities?tripType=outstation&subTripType=roundTrip&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1ODU5NDQzMjQsImp0aSI6IjU0UFBFWnhtbFF3eGRQS0JVV1NVMlFqUDB4bThhWjREekdTZkt6bVVVWWM9IiwiaXNzIjoic2F2YWFyaSIsIm5iZiI6MTU4NTk0NDMyNCwiZXhwIjoxNTg2NTQ5MTI0LCJkYXRhIjp7ImFwaUtleSI6IjU3NmE2NzgzZWE1NGYiLCJhcHBJZCI6IjM5MWM1NGVjYzY4MzgyZjBjMjcxODA1NzM4MjIxNGU1In19.l1Mk1UsxepZGlTm8XAvXictq0XPkVgg6cX6GiFKdyND00YCuW6LisIGwKJC2UDD1yBYbHtiRHQFdLN3jOxE1Hw"), CityEntity.class);
            cityService.createCities(data.getData());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

