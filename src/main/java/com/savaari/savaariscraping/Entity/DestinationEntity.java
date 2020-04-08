package com.savaari.savaariscraping.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "destination-cities")
public class DestinationEntity {
    @Id
    public String id;
    public Integer cityId;
    public String cityName;
    public String Source_city_id;
    public List<String> alias;

    public DestinationEntity() {

    }

    public DestinationEntity(String Source_city_id, Integer cityId, String cityName, List<String> alias) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.Source_city_id = Source_city_id;
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource_city_id() {
        return Source_city_id;
    }

    public void setSource_city_id(String source_city_id) {
        this.Source_city_id = source_city_id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

}
