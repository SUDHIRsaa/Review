package com.example.Review.Repository;

import com.example.Review.entity.DeliveryReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryReviewRepository extends JpaRepository<DeliveryReview, Long> {
    
}
