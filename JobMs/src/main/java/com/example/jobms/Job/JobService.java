package com.example.jobms.Job;

import com.example.jobms.Dto.JobDTO;

import java.util.List;


public interface JobService {
	
	List<JobDTO> findAll();
	void createJob(Job job);
	JobDTO getById(Long id);
	boolean deleteById(Long id);
	boolean updateJob(Long id, Job job);
}
