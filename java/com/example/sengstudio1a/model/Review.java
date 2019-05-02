package com.example.sengstudio1a.model;

public class Review {

//    private final int reviewID;
    private final int reviewerID;
    private final int revieweeID;
    private final String content;

    /**
     * Create a new Review using the given parameters.
     *
     * TODO:
     *  - Generate a unique reviewID
     *
     * @param content Content of the review.
     */
    public Review(int reviewerID, int revieweeID, String content) {
        // reviewID
        this.reviewerID = reviewerID;
        this.revieweeID = revieweeID;
        this.content = content;
    }

}
