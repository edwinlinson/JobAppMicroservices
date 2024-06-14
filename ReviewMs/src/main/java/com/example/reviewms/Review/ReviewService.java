package com.example.reviewms.Review;

import java.util.List;

public interface ReviewService {
	List<Review> getAllReviews(Long companyId);

	boolean addReview(Long companyId, Review review); 
	boolean updateReview(Long id, Review review);
	boolean delReview(Long id);
}
