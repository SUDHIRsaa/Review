package com.example.Review.controller;

import com.example.Review.entity.DeliveryReview;
import com.example.Review.Repository.DeliveryReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-reviews")
public class DeliveryReviewController {

    private final DeliveryReviewRepository deliveryReviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(DeliveryReviewController.class);

    public DeliveryReviewController(DeliveryReviewRepository deliveryReviewRepository) {
        this.deliveryReviewRepository = deliveryReviewRepository;
    }

    @GetMapping
    public List<DeliveryReview> getAllDeliveryReviews() {
        try {
            logger.info("Fetching all delivery reviews");
            return deliveryReviewRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving delivery reviews: {}", e.getMessage());
            throw new RuntimeException("Error retrieving delivery reviews: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryReview> getDeliveryReviewById(@PathVariable Long id) {
        try {
            logger.info("Fetching delivery review with id: {}", id);
            return deliveryReviewRepository.findById(id)
                    .map(review -> ResponseEntity.ok(review))
                    .orElseGet(() -> {
                        logger.warn("Delivery review with id {} not found", id);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    });
        } catch (Exception e) {
            logger.error("Error retrieving delivery review by ID: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<DeliveryReview> createDeliveryReview(@RequestBody DeliveryReview deliveryReview) {
        try {
            logger.info("Creating a new delivery review");
            DeliveryReview savedReview = deliveryReviewRepository.save(deliveryReview);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            logger.error("Error creating delivery review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryReview> updateDeliveryReview(@PathVariable Long id, @RequestBody DeliveryReview deliveryReview) {
        try {
            logger.info("Updating delivery review with id: {}", id);
            deliveryReview.setDeliveryRevId(id);
            DeliveryReview updatedReview = deliveryReviewRepository.save(deliveryReview);
            return ResponseEntity.ok(updatedReview);
        } catch (Exception e) {
            logger.error("Error updating delivery review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryReview(@PathVariable Long id) {
        try {
            logger.info("Deleting delivery review with id: {}", id);
            deliveryReviewRepository.deleteById(id);
            logger.info("Delivery review with id {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting delivery review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
