package com.company.classes;

import com.company.interfaces.Flyable;
import com.company.weather.WeatherTower;

import java.io.IOException;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() throws IOException {
        int height = 0;
        String weather = weatherTower.getWeather(coordinates);
        if (weather.equals("RAIN")) {
            height = coordinates.getHeight() - 5;
            coordinates.setHeight(Math.max(height, 0));
            Writer.writeIntoFile("Baloon#" + name + "(" + id + "): Damn you rain! You messed up my baloon.");
        } else if (weather.equals("FOG")){
            height = coordinates.getHeight() - 3;
            coordinates.setHeight(Math.max(height, 0));
            Writer.writeIntoFile("Baloon#" + name + "(" + id + "): It's foggy...");
        } else if (weather.equals("SUN")) {
            coordinates.setLongitude(coordinates.getLongitude() + 2);
            coordinates.setHeight(coordinates.getHeight() + 4);
            Writer.writeIntoFile("Baloon#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
        } else if (weather.equals("SNOW")){
            height = coordinates.getHeight() - 15;
            coordinates.setHeight(Math.max(height, 0));
            Writer.writeIntoFile("Baloon#" + name + "(" + id + "):  It's snowing. We're gonna crash.");
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
