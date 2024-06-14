package com.example.jobms.external;

import lombok.Data;

@Data
public class Review {
    private Long id;

    private String description;
    private String title;
    private double rating;
}
