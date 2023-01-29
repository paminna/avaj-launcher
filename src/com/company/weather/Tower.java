package com.company.weather;

import com.company.classes.Writer;
import com.company.interfaces.Flyable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) throws IOException {
        observers.add(flyable);
        Writer.writeIntoFile("Tower says: " + flyable.getClass().getSimpleName() + "#"
                                + flyable.getName() + "(" + flyable.getId() + ")"
                                + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) throws IOException {
        observers.remove(flyable);
        Writer.writeIntoFile("Tower says: " + flyable.getClass().getSimpleName() + "#"
                + flyable.getName() + "(" + flyable.getId() + ")"
                + " unregistered from weather tower.");
    }

    protected void conditionsChanged() throws IOException {
        for (int i = 0; i < observers.size(); i++){
            observers.get(i).updateConditions();
        }
    }
}
