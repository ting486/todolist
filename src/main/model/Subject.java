package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void notifyObservers(Item i) {
        for (Observer o : observers) {
            o.update(i);
        }
    }
}
