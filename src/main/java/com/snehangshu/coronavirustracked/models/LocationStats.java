package com.snehangshu.coronavirustracked.models;

public class LocationStats {
    private String State;
    private String country;
    private int latestReport;
    private int differenceFromPreviousDay;
    private int countIndia;



    public int getDifferenceFromPreviousDay() {
        return differenceFromPreviousDay;
    }

    public void setDifferenceFromPreviousDay(int differenceFromPreviousDay) {
        this.differenceFromPreviousDay = differenceFromPreviousDay;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestReport() {
        return latestReport;
    }

    public void setLatestReport(int latestReport) {
        this.latestReport = latestReport;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "State='" + State + '\'' +
                ", country='" + country + '\'' +
                ", latestReport=" + latestReport +
                '}';
    }
}
