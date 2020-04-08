package com.savaari.savaariscraping.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CityEntity {
    public String status;
    public List<Entity> data;

    public CityEntity() {

    }

    @JsonCreator
    public CityEntity(@JsonProperty("status") String status, @JsonProperty("data") List<Entity> data) {
        this.status = status;
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("data")
    private void unpackNested(List<Entity> data) {
        this.data = data;

    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Entity> getData() {
        return data;
    }

    public void setData(List<Entity> data) {
        this.data = data;
    }

}
