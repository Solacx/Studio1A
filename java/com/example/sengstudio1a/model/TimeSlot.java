package com.example.sengstudio1a.model;

import java.util.Date;

public class TimeSlot {

    private final int userID;
    private final String datetime;
    private String desc;

    public TimeSlot(int userID, String desc) {
        this.userID = userID;
        this.desc = desc;
        datetime = new Date().toString();
    }

    // public void remove()

    // public void lock()

    // public void makeBooking()

}
