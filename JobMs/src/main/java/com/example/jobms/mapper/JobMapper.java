package com.example.jobms.mapper;

import com.example.jobms.Dto.JobDTO;
import com.example.jobms.Job.Job;
import com.example.jobms.external.Company;
import com.example.jobms.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());

        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
