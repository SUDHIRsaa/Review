package com.example.Review.controller;

import com.example.Review.entity.CustomerReview;
import com.example.Review.entity.Review;
import com.example.Review.Repository.CustomerReviewRepository;
import com.example.Review.Repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer-reviews")
public class CustomerReviewController {

    private final CustomerReviewRepository customerReviewRepository;
    private final ReviewRepository reviewRepository; 

    private static final Logger logger = LoggerFactory.getLogger(CustomerReviewController.class);

    // Inject both repositories via constructor
    public CustomerReviewController(CustomerReviewRepository customerReviewRepository, ReviewRepository reviewRepository) {
        this.customerReviewRepository = customerReviewRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<CustomerReview> getAllCustomerReviews() {
        logger.info("Fetching all customer reviews");
        return customerReviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerReview> getCustomerReviewById(@PathVariable Long id) {
        logger.info("Fetching customer review with ID {}", id);
        Optional<CustomerReview> review = customerReviewRepository.findById(id);
        if (review.isPresent()) {
            return ResponseEntity.ok(review.get());
        } else {
            logger.warn("Customer review with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public CustomerReview createCustomerReview(@RequestBody CustomerReview customerReview) {
        try {
            Review review = customerReview.getReview();
            // Save the review if it's new (id is null)
            if (review.getReviewId() == null) {
                reviewRepository.save(review);
            }
            // Save the customer review with the customer ID
            return customerReviewRepository.save(customerReview);
        } catch (Exception e) {
            throw new RuntimeException("Error creating customer review: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerReview> updateCustomerReview(@PathVariable Long id, @RequestBody CustomerReview customerReview) {
        logger.info("Updating customer review with ID {}", id);
        Optional<CustomerReview> existingReview = customerReviewRepository.findById(id);
        if (existingReview.isPresent()) {
            customerReview.setCustomerRevId(id);
            return ResponseEntity.ok(customerReviewRepository.save(customerReview));
        } else {
            logger.warn("Customer review with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerReview(@PathVariable Long id) {
        logger.info("Deleting customer review with ID {}", id);
        if (customerReviewRepository.existsById(id)) {
            customerReviewRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            logger.warn("Customer review with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
