package com.example.ruladmin;


public class Trip {
    private Location location;
    private String key;
    private String name;
    private String difficulty;
    private String date;
    private String maxPeople;
    private String available;

    public Trip(){

    }

    public Trip(String key, String name, Location location, String difficulty, String date, String available, String maxPeople){
        this.key = key;
        this.name = name;
        this.location = location;
        this.difficulty = difficulty;
        this.date = date;
        this.available = available;
        this.maxPeople = maxPeople;
    }

    public String getKey(){ return this.key; }

    public String getName(){
        return this.name;
    }

    public String getLatitude(){
        return this.location.getLatitude();
    }

    public String getLongitude(){
        return this.location.getLongitude();
    }

    public String getDifficulty(){
        return this.difficulty;
    }

    public String getDate(){
        return this.date;
    }

    public String getAvailable() { return this.available; }

    public String getLocationTag() { return this.location.getTag(); }

    public String getMaxPeople() { return this.maxPeople; }
}
