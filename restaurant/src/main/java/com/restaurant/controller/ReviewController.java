package com.restaurant.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.pojo.Review;
import com.restaurant.service.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	@PostMapping("")
	public Review addReview(@RequestBody Review review) {
		return reviewService.addReview(review);
	}
	
	@GetMapping("")
	public List<Review> getAllReviews(){
		return reviewService.getAllReviews();
	}
	
	@GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found with ID: " + reviewId);
        }
    }
}