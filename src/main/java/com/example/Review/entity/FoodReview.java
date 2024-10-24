package com.example.Review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "food_review")
public class FoodReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodrev_id")
    private Long foodRevId;

    @Column(name = "food_id", nullable = false)
    private int foodId;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customerrev_id")
    private CustomerReview customerReview;
    
    @Column(name = "taste_rating")
    private int tasteRating;
    @Column(name = "presentation_rating")
    
    private int presentationRating;
    @Column(name = "portionsize_rating")
    private int portionSizeRating;

    @Column(name = "feedback")
    private String feedback;

    public Long getFoodRevId() {
        return foodRevId;
    }

    public void setFoodRevId(Long foodRevId) {
        this.foodRevId = foodRevId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public CustomerReview getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(CustomerReview customerReview) {
        this.customerReview = customerReview;
    }

    public int getTasteRating() {
        return tasteRating;
    }

    public void setTasteRating(int tasteRating) {
        this.tasteRating = tasteRating;
    }

    public int getPresentationRating() {
        return presentationRating;
    }

    public void setPresentationRating(int presentationRating) {
        this.presentationRating = presentationRating;
    }

    public int getPortionSizeRating() {
        return portionSizeRating;
    }

    public void setPortionSizeRating(int portionSizeRating) {
        this.portionSizeRating = portionSizeRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
