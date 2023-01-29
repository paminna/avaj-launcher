package com.company.weather;

import com.company.classes.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;

    private String[] weather = {"RAIN", "SUN", "FOG", "SNOW"};

    private WeatherProvider(){
    }

    public static WeatherProvider getProvider(){
        if (weatherProvider == null){
            return new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){
        return weather[(coordinates.getHeight() + coordinates.getLongitude() + coordinates.getLatitude()) % 4];
    }

}
