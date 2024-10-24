package com.example.Review.controller;

import com.example.Review.entity.LocationReview;
import com.example.Review.Repository.LocationReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location-reviews")
public class LocationReviewController {

    private final LocationReviewRepository locationReviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(LocationReviewController.class);

    public LocationReviewController(LocationReviewRepository locationReviewRepository) {
        this.locationReviewRepository = locationReviewRepository;
    }

    @GetMapping
    public List<LocationReview> getAllLocationReviews() {
        try {
            logger.info("Fetching all location reviews");
            return locationReviewRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving location reviews: {}", e.getMessage());
            throw new RuntimeException("Error retrieving location reviews: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationReview> getLocationReviewById(@PathVariable Long id) {
        try {
            logger.info("Fetching location review with id: {}", id);
            return locationReviewRepository.findById(id)
                    .map(review -> ResponseEntity.ok(review))
                    .orElseGet(() -> {
                        logger.warn("Location review with id {} not found", id);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    });
        } catch (Exception e) {
            logger.error("Error retrieving location review by ID: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<LocationReview> createLocationReview(@RequestBody LocationReview locationReview) {
        try {
            logger.info("Creating a new location review");
            LocationReview savedReview = locationReviewRepository.save(locationReview);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            logger.error("Error creating location review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationReview> updateLocationReview(@PathVariable Long id, @RequestBody LocationReview locationReview) {
        try {
            logger.info("Updating location review with id: {}", id);
            locationReview.setLocationRevId(id);
            LocationReview updatedReview = locationReviewRepository.save(locationReview);
            return ResponseEntity.ok(updatedReview);
        } catch (Exception e) {
            logger.error("Error updating location review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationReview(@PathVariable Long id) {
        try {
            logger.info("Deleting location review with id: {}", id);
            locationReviewRepository.deleteById(id);
            logger.info("Location review with id {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting location review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
