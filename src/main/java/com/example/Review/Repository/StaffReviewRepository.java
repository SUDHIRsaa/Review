package com.example.Review.Repository;

import com.example.Review.entity.StaffReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffReviewRepository extends JpaRepository<StaffReview, Long> {
}
