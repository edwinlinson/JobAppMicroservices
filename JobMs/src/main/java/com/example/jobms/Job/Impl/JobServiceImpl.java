package com.example.jobms.Job.Impl;

import com.example.jobms.Job.Job;
import com.example.jobms.Job.JobRepo;
import com.example.jobms.Job.JobService;
import com.example.jobms.clients.CompanyClient;
import com.example.jobms.clients.ReviewClient;
import com.example.jobms.external.Company;
import com.example.jobms.Dto.JobDTO;
import com.example.jobms.external.Review;
import com.example.jobms.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//	private Long nextId =1L; 
//	List<Job> jobs = new ArrayList<>();
	
	private JobRepo jobRepo;
	private CompanyClient companyClient;
	private ReviewClient reviewClient;
	
	
	public JobServiceImpl(JobRepo jobRepo,CompanyClient companyClient, ReviewClient reviewClient) {
	    this.companyClient = companyClient;
	    this.reviewClient = reviewClient;
		this.jobRepo = jobRepo;
}

	@Autowired
	private RestTemplate restTemplate;


	@Override
	public List<JobDTO> findAll() {
		List<JobDTO> jobDTOS = new ArrayList<>();
		List<Job> jobs = jobRepo.findAll();
		for (Job job:jobs){
			JobDTO jobDTO = convertToDto(job);
			jobDTOS.add(jobDTO);
		}

		return jobDTOS;
	}

	@Override
	public void createJob(Job job) {
		jobRepo.save(job);
	}

	@Override
	public JobDTO getById(Long id) {
		Job job = jobRepo.findById(id).orElse(null);
		return convertToDto(job);
	}

	@Override
	public boolean deleteById(Long id) {
		try {
		jobRepo.deleteById(id);
		return true;
		}catch(Exception e) {
			return false;
		}
		
	}

//	@Override
//	public boolean deleteIterator(Long id) {
//		Iterator<Job> iterator = jobs.iterator();
//		while(iterator.hasNext()) {
//			Job job = iterator.next();
//			System.out.println(" Job is : "+job);
//			iterator.remove();
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean updateJob(Long id, Job Updatedjob) {
		Optional<Job> optionalJob = jobRepo.findById(id);
			if(optionalJob.isPresent()) {
				Job job = optionalJob.get();
				job.setDescription(Updatedjob.getDescription());
				job.setLocation(Updatedjob.getLocation());
				job.setMaxSalary(Updatedjob.getMaxSalary());
				job.setMinSalary(Updatedjob.getMinSalary());
				job.setTitle(Updatedjob.getTitle());
				jobRepo.save(job);
				return true;
			}
		return false;
	}
	private JobDTO convertToDto(Job job){
//		Company company = restTemplate.getForObject("http://COMPANYMS:8082/company/"+job.getCompanyId(), Company.class);
		Company company = companyClient.getCompany(job.getCompanyId());
		ResponseEntity<List<Review>> response = restTemplate.exchange("http://REVIEWMS:8083/reviews?companyId=" + job.getCompanyId(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<>() {
				});
		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
		JobDTO jobDTO = JobMapper.mapToJobDTO(job,company,reviews);
		jobDTO.setCompany(company);
		return jobDTO;
	}
}
