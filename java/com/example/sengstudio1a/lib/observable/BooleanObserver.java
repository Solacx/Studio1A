package com.example.sengstudio1a.lib.observable;

import java.util.Observable;
import java.util.Observer;

public interface BooleanObserver extends Observer {

    @Override
    void update(Observable o, Object arg);

}
