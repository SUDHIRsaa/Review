package com.example.Review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer_Review")
public class CustomerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerrev_id")
    private Long customerrevId;

    @ManyToOne
    @JoinColumn(name = "review_id",nullable = false)
    private Review review;
    @Column(name = "Customer_id", nullable = false)
    private int customerId;

    public Long getCustomerRevId() {
        return customerrevId;
    }

    public void setCustomerRevId(Long customerRevId) {
        this.customerrevId = customerRevId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
