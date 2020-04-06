package com.savaari.savaariscraping.Entity;
import java.util.List;
public class Entity {
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
