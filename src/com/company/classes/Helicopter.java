package com.company.classes;

import com.company.interfaces.Flyable;
import com.company.weather.WeatherTower;

import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() throws IOException {
        int height = 0;
        String weather = weatherTower.getWeather(coordinates);
        if (weather.equals("RAIN")) {
            coordinates.setLongitude(coordinates.getLongitude() + 5);
            Writer.writeIntoFile("Helicopter#" + name + "(" + id + "): Oh, it's rain!");
        } else if (weather.equals("FOG")){
            coordinates.setLongitude(coordinates.getLongitude() + 1);
            Writer.writeIntoFile("Helicopter#" + name + "(" + id + "): It's foggy...");
        } else if (weather.equals("SUN")) {
            coordinates.setLongitude(coordinates.getLongitude() + 10);
            coordinates.setHeight(coordinates.getHeight() + 2);
            Writer.writeIntoFile("Helicopter#" + name + "(" + id + "): This is hot.");
        } else if (weather.equals("SNOW")){
            height = coordinates.getHeight() - 12;
            coordinates.setHeight(Math.max(height, 0));
            Writer.writeIntoFile("Helicopter#" + name + "(" + id + "): My rotor is going to freeze!");
        }
        if (coordinates.getHeight() == 0) {
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }
}
