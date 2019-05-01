package com.example.sengstudio1a.model;

public class User {

//    private final int userID;
    private final String username;
    private final String password;
    private final String phone;
    private final String email;
    private final String type;

    /**
     * Create a new User using the given parameters.
     *
     * TODO:
     *  - Generate a unique userID
     */
    public User(
            String username, String password, String phone, String email,
            String type) {
        // userID
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.type = type;
    }

    // public Review[] getReviews()

    // public Booking[] getBookings()

    // public Booking[] getDonationHistory()

}
