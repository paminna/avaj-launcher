package com.company.weather;

import com.company.classes.Coordinates;

import java.io.IOException;

public class WeatherTower extends Tower {
    private final WeatherProvider weatherProvider = WeatherProvider.getProvider();

    public String getWeather(Coordinates coordinates){
        return weatherProvider.getCurrentWeather(coordinates);
    }

    private void changeWeather() throws IOException {
        conditionsChanged();
    }
}
