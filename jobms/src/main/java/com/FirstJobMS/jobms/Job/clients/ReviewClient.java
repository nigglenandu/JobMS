package com.FirstJobMS.jobms.Job.clients;

import com.FirstJobMS.jobms.Job.External.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "review-service",
        url ="${review-service.url}" )
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getReviews(@PathVariable("companyId") Long CompanyId);
}
