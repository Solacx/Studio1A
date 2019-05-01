package com.example.sengstudio1a.model;

import java.util.Date;

public class Booking {

    private final int donorID;
    private final TimeSlot timeslot;
    private final String datetime;
    // Can be pending (default), approved, declined or completed.
    private String state = "pending";

    /**
     * Create a new Booking using the given parameters.
     *
     * @param timeslot TimeSlot the Booking is being made on
     */
    public Booking(int donorID, TimeSlot timeslot) {
        this.donorID = donorID;
        this.timeslot = timeslot;
        datetime = new Date().toString();
    }

    // public void remove()

    public void approve() {
        state = "approved";
    }

    public void decline() {
        state = "declined";
    }

    public void complete() {
        state = "completed";
    }

    public String getState() {
        return state;
    }

}
