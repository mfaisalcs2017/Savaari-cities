package com.savaari.savaariscraping.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "cities")
public class Entity {
    @Id
    public String id;
    private Integer cityId;
    private String cityName;
    private List<String> alias;

    public Entity() {
    }

    @Override
    public String toString() {
        return "Entity{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", alias=" + alias +
                '}';
    }

    public Entity(Integer cityId, String cityName, List<String> alias) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.alias = alias;
    }
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

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
