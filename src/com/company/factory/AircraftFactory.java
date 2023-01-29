package com.company.factory;

import com.company.classes.*;
import com.company.error.LauncherException;
import com.company.interfaces.Flyable;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws IOException, LauncherException {
        Map<Object, String> aircrafts = new HashMap<>();
        List<String> names = new LinkedList<>();
        names.add("Helicopter");
        names.add("Baloon");
        names.add("JetPlane");
        if (latitude >= 0 && longitude >= 0 && height >= 0){
            if (height > 100)
            {
                height = 100;
            }
            if (type.equals(names.get(0))){
                return new Helicopter(name, new Coordinates(longitude, latitude, height));
            } else if (type.equals(names.get(1))){
                return new Baloon(name, new Coordinates(longitude, latitude, height));
            } else if (type.equals(names.get(2))){
                return new JetPlane(name, new Coordinates(longitude, latitude, height));
            } else{
                Writer.writeIntoFile("ERROR: wrong type");
                throw new LauncherException("No such type of flyable object");
            }
        }
        return null;
    }
}
