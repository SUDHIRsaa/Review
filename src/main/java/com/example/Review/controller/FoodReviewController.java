package com.example.Review.controller;

import com.example.Review.entity.FoodReview;
import com.example.Review.Repository.FoodReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-reviews")
public class FoodReviewController {

    private final FoodReviewRepository foodReviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(FoodReviewController.class);

    public FoodReviewController(FoodReviewRepository foodReviewRepository) {
        this.foodReviewRepository = foodReviewRepository;
    }

    @GetMapping
    public List<FoodReview> getAllFoodReviews() {
        try {
            logger.info("Fetching all food reviews");
            return foodReviewRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving food reviews: {}", e.getMessage());
            throw new RuntimeException("Error retrieving food reviews: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodReview> getFoodReviewById(@PathVariable Long id) {
        try {
            logger.info("Fetching food review with id: {}", id);
            return foodReviewRepository.findById(id)
                    .map(review -> ResponseEntity.ok(review))
                    .orElseGet(() -> {
                        logger.warn("Food review with id {} not found", id);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    });
        } catch (Exception e) {
            logger.error("Error retrieving food review by ID: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<FoodReview> createFoodReview(@RequestBody FoodReview foodReview) {
        try {
            logger.info("Creating a new food review");
            FoodReview savedReview = foodReviewRepository.save(foodReview);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (Exception e) {
            logger.error("Error creating food review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodReview> updateFoodReview(@PathVariable Long id, @RequestBody FoodReview foodReview) {
        try {
            logger.info("Updating food review with id: {}", id);
            foodReview.setFoodRevId(id);
            FoodReview updatedReview = foodReviewRepository.save(foodReview);
            return ResponseEntity.ok(updatedReview);
        } catch (Exception e) {
            logger.error("Error updating food review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodReview(@PathVariable Long id) {
        try {
            logger.info("Deleting food review with id: {}", id);
            foodReviewRepository.deleteById(id);
            logger.info("Food review with id {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting food review: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
