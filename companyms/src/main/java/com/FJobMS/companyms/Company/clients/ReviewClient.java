package com.FJobMS.companyms.Company.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "review-service",
        url ="${review-service.url}" )
public interface ReviewClient {

    @GetMapping("/review/averageRating")
    Double getAverageRatingForCompany(
            @RequestParam("companyId") Long companyId);
}
