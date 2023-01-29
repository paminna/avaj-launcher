package com.company.classes;

import com.company.interfaces.Flyable;
import com.company.weather.WeatherTower;

import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() throws IOException {
        int height = 0;
        String weather = weatherTower.getWeather(coordinates);
        if (weather.equals("RAIN")) {
            coordinates.setLatitude(coordinates.getLatitude() + 5);
            Writer.writeIntoFile("JetPlane#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
        } else if (weather.equals("FOG")){
            coordinates.setLatitude(coordinates.getLatitude() + 1);
            Writer.writeIntoFile("JetPlane#" + name + "(" + id + "): It's foggy...");
        } else if (weather.equals("SUN")) {
            coordinates.setLongitude(coordinates.getLongitude() + 10);
            coordinates.setHeight(coordinates.getHeight() + 2);
            Writer.writeIntoFile("JetPlane#" + name + "(" + id + "): Here is so sunny!");
        } else if (weather.equals("SNOW")){
            height = coordinates.getHeight() - 7;
            coordinates.setHeight(Math.max(height, 0));
            Writer.writeIntoFile("JetPlane#" + name + "(" + id + "): OMG! Winter is coming!");
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
