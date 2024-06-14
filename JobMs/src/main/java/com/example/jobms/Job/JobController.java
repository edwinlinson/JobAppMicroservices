package com.example.jobms.Job;

import com.example.jobms.Dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	
	@GetMapping
	public ResponseEntity<List<JobDTO>>  getAll(){
		return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<JobDTO> getJob(@PathVariable Long id){
		JobDTO jobDTO = jobService.getById(id);
		if(jobDTO != null) {
			return ResponseEntity.ok(jobDTO);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("Created succesfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		if(jobService.deleteById(id)) {
			return ResponseEntity.ok("Deleted Sucessfully");
		}
		return new  ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){
		if(jobService.updateJob(id,job)) {
		return new ResponseEntity<>("Updated Successfully",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
