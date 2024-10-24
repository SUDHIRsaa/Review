package com.example.Review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Location_Review")
public class LocationReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationrev_id")
    private Long locationRevId;
    @Column(name = "Location_id", nullable = false)
    private int locationId;

    @ManyToOne
    @JoinColumn(name = "customerrev_id",nullable = false)
    private CustomerReview customerReview;
    @Column(name = "Accessibility_Rating")
    private int accessibilityRating;

    @Column(name = "Ambiance_Rating")
    private int ambianceRating;

    @Column(name = "Cleanliness_Rating")
    private int cleanlinessRating;

    @Column(name = "Feedback")
    private String feedback;

    public Long getLocationRevId() {
        return locationRevId;
    }

    public void setLocationRevId(Long locationRevId) {
        this.locationRevId = locationRevId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public CustomerReview getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(CustomerReview customerReview) {
        this.customerReview = customerReview;
    }

    public int getAccessibilityRating() {
        return accessibilityRating;
    }

    public void setAccessibilityRating(int accessibilityRating) {
        this.accessibilityRating = accessibilityRating;
    }

    public int getAmbianceRating() {
        return ambianceRating;
    }

    public void setAmbianceRating(int ambianceRating) {
        this.ambianceRating = ambianceRating;
    }

    public int getCleanlinessRating() {
        return cleanlinessRating;
    }

    public void setCleanlinessRating(int cleanlinessRating) {
        this.cleanlinessRating = cleanlinessRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
