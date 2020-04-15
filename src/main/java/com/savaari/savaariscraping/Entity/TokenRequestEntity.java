package com.savaari.savaariscraping.Entity;

import java.util.List;

public class TokenRequestEntity {
    public String status;
    public TokenData data;


    public TokenRequestEntity() {

    }

    public TokenRequestEntity(String status, TokenData data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TokenData getData() {
        return data;
    }

    public void setData(TokenData data) {
        this.data = data;
    }
}
