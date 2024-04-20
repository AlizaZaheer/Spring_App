package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.pojo.Review;
import com.restaurant.repo.ReviewRepo;
import com.restaurant.service.ReviewService;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepo reviewRepo;

    @Test
    public void testAddReview() {
        Review review = new Review();
        review.setReviewName("Test User");
        review.setReviewStar(4);
        review.setReviewComment("Great food!");

        when(reviewRepo.save(review)).thenReturn(review);

        Review addedReview = reviewService.addReview(review);

        assertEquals(review, addedReview);
        verify(reviewRepo).save(review);
    }

    @Test
    public void testGetAllReviews() {
        List<Review> reviews = new ArrayList<>();
        Review review1 = new Review();
        review1.setReviewName("Test User1");
        review1.setReviewStar(4);
        review1.setReviewComment("Great food!");
        Review review2 = new Review();
        review2.setReviewName("Test User2");
        review2.setReviewStar(5);
        review2.setReviewComment("Excellent!");
        reviews.add(review1);
        reviews.add(review2);

        when(reviewRepo.findAll()).thenReturn(reviews);

        List<Review> retrievedReviews = reviewService.getAllReviews();

        assertEquals(reviews, retrievedReviews);
    }
}