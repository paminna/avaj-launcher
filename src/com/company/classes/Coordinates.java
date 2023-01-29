package com.company.classes;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        if (latitude < 0){
            this.latitude = 0;
        } else if (longitude < 0){
            this.longitude = 0;
        } else if (height < 0){
            this.height = 0;
        } else {
            this.longitude = longitude;
            this.latitude = latitude;
            this.height = height;
        }
    }

    public int getLongitude(){
        return longitude;
    }

    public int getLatitude(){
        return latitude;
    }

    public int getHeight(){
        return height;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
