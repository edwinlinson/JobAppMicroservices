package com.example.reviewms.Review.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewMessage {
    private Long id;
    private double rating;
    private Long companyId;
    private String title;
    private String description;
}
