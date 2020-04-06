package com.savaari.savaariscraping.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "cities")
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
