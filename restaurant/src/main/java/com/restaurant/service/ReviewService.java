package com.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pojo.Review;
import com.restaurant.repo.ReviewRepo;

@Service
public class ReviewService {

	@Autowired
	ReviewRepo reviewRepo;
	public Review addReview(Review review) {
		return reviewRepo.save(review);
	}
	public List<Review> getAllReviews(){
		return reviewRepo.findAll();
	}
	public Review getReviewById(Long reviewId) {
        Optional<Review> optionalReview = reviewRepo.findById(reviewId);
        if (optionalReview.isPresent()) {
            return optionalReview.get();
        } else {
            // Handle the case when review with the specified ID is not found
            throw new RuntimeException("Review not found with ID: " + reviewId);
        }
      
    }
}