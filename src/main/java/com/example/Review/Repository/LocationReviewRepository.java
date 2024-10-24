package com.example.Review.Repository;

import com.example.Review.entity.LocationReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationReviewRepository extends JpaRepository<LocationReview, Long> {
}