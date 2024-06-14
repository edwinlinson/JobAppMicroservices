package com.example.reviewms.Review;

import com.example.reviewms.Review.Impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private ReviewServiceImpl service;
	
	public ReviewController(ReviewServiceImpl service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId){
		List<Review> reviews = service.getAllReviews(companyId);
		return new ResponseEntity<>(reviews,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
		
		if(service.addReview(companyId,review)) {
			
		return new ResponseEntity<>("Review added succesfully", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Review not added succesfully", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Review review){
		if(service.updateReview(id,review)) {
			return new ResponseEntity<>("Review updated succesfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Review not updated succesfully",HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Long id){
		if(service.delReview(id)) {
			return new ResponseEntity<>("Review deleted succesfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Review not updated succesfully",HttpStatus.BAD_REQUEST);
	}

}
