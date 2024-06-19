package com.example.companyms.Company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "reviewms")
public interface ReviewClient {

    @GetMapping("/reviews/rating")
    List<Double> getRating(@RequestParam Long companyId);
}
