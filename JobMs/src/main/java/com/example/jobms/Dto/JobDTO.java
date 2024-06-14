package com.example.jobms.Dto;

import com.example.jobms.Job.Job;
import com.example.jobms.external.Company;
import com.example.jobms.external.Review;
import lombok.Data;

import java.util.List;

@Data
public class JobDTO {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    private Company company;
    private List<Review> reviews;

}
