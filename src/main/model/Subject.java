package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    // EFFECTS: gets observers
    public List<Observer> getObservers() {
        return observers;
    }

    // MODIFIES: observers
    // EFFECTS: adds o to observers
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    // MODIFIES: o
    // EFFECTS: iterates over observers and update each o based on i
    public void notifyObservers(Item i) {
        for (Observer o : observers) {
            o.update(i);
        }
    }
}
