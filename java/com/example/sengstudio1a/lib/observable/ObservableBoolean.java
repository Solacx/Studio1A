package com.example.sengstudio1a.lib.observable;

import java.util.Observable;

/**
 * This class contains a single boolean variable that can be attached
 * to any number of Observers.
 */
public class ObservableBoolean extends Observable {

    private boolean value;

    public ObservableBoolean(boolean value) {
        this.value = value;
    }

    /**
     * Set the wrapped value.
     *
     * Set the wrapped value and notify all of its Observers. The
     * Observer will receive a reference to wrapped value.
     *
     * @param value new value
     */
    public void setValue(boolean value) {
        this.value = value;
        setChanged();
        notifyObservers(this.value);
    }
}
