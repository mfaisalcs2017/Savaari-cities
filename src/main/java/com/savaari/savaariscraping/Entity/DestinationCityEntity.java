package com.savaari.savaariscraping.Entity;

import java.util.List;

public class DestinationCityEntity {

    public String status;
    public List<DestinationEntity> data;

    public DestinationCityEntity() {
    }

    public DestinationCityEntity(String status, List<DestinationEntity> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DestinationEntity> getData() {
        return data;
    }

    public void setData(List<DestinationEntity> data) {
        this.data = data;
    }

}
