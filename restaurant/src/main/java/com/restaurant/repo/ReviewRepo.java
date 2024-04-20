package com.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.pojo.Review;

public interface ReviewRepo extends JpaRepository<Review, Long>{

}
