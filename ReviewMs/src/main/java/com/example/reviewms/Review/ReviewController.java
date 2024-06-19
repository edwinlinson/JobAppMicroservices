package com.example.reviewms.Review;

import com.example.reviewms.Review.Impl.ReviewServiceImpl;
import com.example.reviewms.Review.Messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private ReviewServiceImpl service;
	private ReviewMessageProducer reviewMessageProducer;
	
	public ReviewController(ReviewServiceImpl service,ReviewMessageProducer reviewMessageProducer) {
		this.service = service;
		this.reviewMessageProducer = reviewMessageProducer;
	}

	@GetMapping
	public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId){
		List<Review> reviews = service.getAllReviews(companyId);
		return new ResponseEntity<>(reviews,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
		if(service.addReview(companyId,review)) {
		reviewMessageProducer.sendMessage(review);
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

	@GetMapping("/rating")
	public ResponseEntity<List<Double>> getRating(@RequestParam Long companyId){
		List<Double> returnlist =new ArrayList<>();
		List<Review> reviews = service.getAllReviews(companyId);
		double average = reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
		List<Double> sorted = reviews.stream().
				mapToDouble(Review::getRating).
				sorted().boxed().toList();
		Double median;
		if(sorted.isEmpty()){
			median =0.0;
		} else if (sorted.size()%2 !=0) {
			median = sorted.get(sorted.size()/2);
		}else {
			median = ( (sorted.get(sorted.size()/2-1)) + sorted.get(sorted.size()/2) ) /2;
		}
		returnlist.add(average);
		returnlist.add(median);
		return new ResponseEntity<>(returnlist,HttpStatus.OK);
	}

}
