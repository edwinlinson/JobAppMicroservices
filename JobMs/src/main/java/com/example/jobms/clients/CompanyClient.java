package com.example.jobms.clients;

import com.example.jobms.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS")
public interface CompanyClient {

    @GetMapping("/company/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
