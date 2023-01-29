package com.company.interfaces;

import com.company.classes.Coordinates;
import com.company.weather.WeatherTower;

import java.io.IOException;

public interface Flyable {

    void updateConditions() throws IOException;

    void registerTower(WeatherTower weatherTower);

    long getId();
    String getName();
    Coordinates getCoordinates();
}
