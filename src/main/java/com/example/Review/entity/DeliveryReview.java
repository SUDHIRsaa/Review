package com.example.Review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Delivery_Review")
public class DeliveryReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deliveryrev_id")
    private Long deliveryRevId;

    @Column(name = "Delivery_ID", nullable = false)
    private int deliveryId;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customerrev_id")
    private CustomerReview customerReview;
    @Column(name = "Timeliness_Rating")
    private int timelinessRating;
    @Column(name = "ConditionUponArrival_Rating")
    private int conditionUponArrivalRating;
    @Column(name = "DeliveryStaff_Rating")
    private int deliveryStaffRating;
    @Column(name = "Feedback")
    private String feedback;

    public Long getDeliveryRevId() {
        return deliveryRevId;
    }

    public void setDeliveryRevId(Long deliveryRevId) {
        this.deliveryRevId = deliveryRevId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public CustomerReview getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(CustomerReview customerReview) {
        this.customerReview = customerReview;
    }

    public int getTimelinessRating() {
        return timelinessRating;
    }

    public void setTimelinessRating(int timelinessRating) {
        this.timelinessRating = timelinessRating;
    }

    public int getConditionUponArrivalRating() {
        return conditionUponArrivalRating;
    }

    public void setConditionUponArrivalRating(int conditionUponArrivalRating) {
        this.conditionUponArrivalRating = conditionUponArrivalRating;
    }

    public int getDeliveryStaffRating() {
        return deliveryStaffRating;
    }

    public void setDeliveryStaffRating(int deliveryStaffRating) {
        this.deliveryStaffRating = deliveryStaffRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
