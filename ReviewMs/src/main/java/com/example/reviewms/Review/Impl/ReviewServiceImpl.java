package com.example.reviewms.Review.Impl;


import com.example.reviewms.Review.Review;
import com.example.reviewms.Review.ReviewRepository;
import com.example.reviewms.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private ReviewRepository repository;
	
	public ReviewServiceImpl(ReviewRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
        return repository.findByCompanyId(companyId);
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		if (companyId != null && review!=null){
			review.setCompanyId(companyId);
			repository.save(review);
			return true;}
		return false;
	}
	@Override
	public boolean updateReview(Long id, Review updatedReview) {
		Review review = repository.findById(id).orElse(null);
		if(review!=null ) {
			review.setTitle(updatedReview.getTitle());
			review.setRating(updatedReview.getRating());
			review.setDescription(updatedReview.getDescription());
			review.setCompanyId(updatedReview.getCompanyId());
			review.setId(id);
			repository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public boolean delReview(Long id) {
		Review review = repository.findById(id).orElse(null);
		if(review !=null) {
			repository.delete(review);
			return true;
		}
		return false;
	}

}
