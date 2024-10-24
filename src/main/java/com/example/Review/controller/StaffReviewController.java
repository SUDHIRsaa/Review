package com.example.Review.controller;

import com.example.Review.entity.StaffReview;
import com.example.Review.Repository.StaffReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff-reviews")
public class StaffReviewController {

    private final StaffReviewRepository staffReviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(StaffReviewController.class);

    public StaffReviewController(StaffReviewRepository staffReviewRepository) {
        this.staffReviewRepository = staffReviewRepository;
    }

    @GetMapping
    public List<StaffReview> getAllStaffReviews() {
        try {
            logger.info("Fetching all staff reviews");
            return staffReviewRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving staff reviews: {}", e.getMessage());
            throw new RuntimeException("Error retrieving staff reviews: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffReview> getStaffReviewById(@PathVariable Long id) {
        logger.info("Fetching staff review with id: {}", id);
        Optional<StaffReview> review = staffReviewRepository.findById(id);
        if (review.isPresent()) {
            return ResponseEntity.ok(review.get());
        } else {
            logger.warn("Staff review with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<StaffReview> createStaffReview(@RequestBody StaffReview staffReview) {
        try {
            logger.info("Creating a new staff review");
            // Set customerReview to null if it's not provided
            if (staffReview.getCustomerReview() == null) {
                staffReview.setCustomerReview(null);
            }
            StaffReview savedReview = staffReviewRepository.save(staffReview);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            logger.error("Error creating staff review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffReview> updateStaffReview(@PathVariable Long id, @RequestBody StaffReview staffReview) {
        try {
            logger.info("Updating staff review with id: {}", id);
            Optional<StaffReview> existingReview = staffReviewRepository.findById(id);
            if (existingReview.isPresent()) {
                staffReview.setStaffRevId(id);
                return ResponseEntity.ok(staffReviewRepository.save(staffReview));
            } else {
                logger.warn("Staff review with id {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            logger.error("Error updating staff review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffReview(@PathVariable Long id) {
        try {
            logger.info("Deleting staff review with id: {}", id);
            if (staffReviewRepository.existsById(id)) {
                staffReviewRepository.deleteById(id);
                logger.info("Staff review with id {} deleted successfully", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Staff review with id {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            logger.error("Error deleting staff review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
