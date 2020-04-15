package com.savaari.savaariscraping.Entity;

public class TokenData {
    public String token;
    public String date;
    public String time_zone;

    @Override
    public String toString() {
        return "TokenData{" +
                "token='" + token + '\'' +
                ", date='" + date + '\'' +
                ", time_zone='" + time_zone + '\'' +
                '}';
    }

    public TokenData() {

    }

    public TokenData(String token, String date, String time_zone) {
        this.token = token;
        this.date = date;
        this.time_zone = time_zone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }
}
