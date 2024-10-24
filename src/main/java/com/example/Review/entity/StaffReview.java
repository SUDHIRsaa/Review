package com.example.Review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Staff_Review")
public class StaffReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffrev_id")
    private Long staffRevId;

    @Column(name = "Staff_ID", nullable = false)
    private int staffId;

    @ManyToOne(optional = true) // Allowing customerReview to be optional
    @JoinColumn(name = "customerStaff_id", referencedColumnName = "customerrev_id")
    private CustomerReview customerReview; // This can be null

    @Column(name = "Politeness_Rating")
    private int politenessRating;

    @Column(name = "Efficiency_Rating")
    private int efficiencyRating;

    @Column(name = "Knowledge_Rating")
    private int knowledgeRating;

    @Column(name = "Feedback")
    private String feedback;

    // Getters and Setters
    public Long getStaffRevId() {
        return staffRevId;
    }

    public void setStaffRevId(Long staffRevId) {
        this.staffRevId = staffRevId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public CustomerReview getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(CustomerReview customerReview) {
        this.customerReview = customerReview;
    }

    public int getPolitenessRating() {
        return politenessRating;
    }

    public void setPolitenessRating(int politenessRating) {
        this.politenessRating = politenessRating;
    }

    public int getEfficiencyRating() {
        return efficiencyRating;
    }

    public void setEfficiencyRating(int efficiencyRating) {
        this.efficiencyRating = efficiencyRating;
    }

    public int getKnowledgeRating() {
        return knowledgeRating;
    }

    public void setKnowledgeRating(int knowledgeRating) {
        this.knowledgeRating = knowledgeRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
