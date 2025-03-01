package com.FirstJobMS.jobms.Job.mapper;

import com.FirstJobMS.jobms.Job.Dto.JobDTO;
import com.FirstJobMS.jobms.Job.External.Company;
import com.FirstJobMS.jobms.Job.External.Review;
import com.FirstJobMS.jobms.Job.Job;

import java.util.List;


public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(
            Job job,
            Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;

    }
}
