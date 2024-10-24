package com.example.Review.controller;

import com.example.Review.entity.Review;
import com.example.Review.Repository.ReviewRepository; // Ensure this import is correct and the ReviewRepository interface exists in this package
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        try {
            List<Review> reviews = reviewRepository.findAll();
            logger.info("Fetched {} reviews", reviews.size());
            return reviews;
        } catch (Exception e) {
            logger.error("Error retrieving reviews: {}", e.getMessage());
            throw new RuntimeException("Error retrieving reviews: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        try {
            Review review = reviewRepository.findById(id).orElse(null);
            if (review != null) {
                logger.info("Fetched review with id: {}", id);
                return ResponseEntity.ok(review);
            } else {
                logger.warn("Review with id {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error retrieving review by ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        try {
            Review savedReview = reviewRepository.save(review);
            logger.info("Created review with id: {}", savedReview.getReviewId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            logger.error("Error creating review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        try {
            if (!reviewRepository.existsById(id)) {
                logger.warn("Review with id {} not found for update", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            review.setReviewId(id);
            Review updatedReview = reviewRepository.save(review);
            logger.info("Updated review with id: {}", id);
            return ResponseEntity.ok(updatedReview);
        } catch (Exception e) {
            logger.error("Error updating review with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            if (reviewRepository.existsById(id)) {
                reviewRepository.deleteById(id);
                logger.info("Deleted review with id: {}", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Review with id {} not found for deletion", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Error deleting review with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
