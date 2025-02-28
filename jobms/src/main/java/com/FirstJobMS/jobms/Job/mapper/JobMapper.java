package com.FirstJobMS.jobms.Job.mapper;

import com.FirstJobMS.jobms.Job.Dto.JobWithCompanyDTO;
import com.FirstJobMS.jobms.Job.External.Company;
import com.FirstJobMS.jobms.Job.Job;


public class JobMapper {
    public static JobWithCompanyDTO mapToJobWithCompanyDTO(
        Job job,
        Company company){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;

    }
}
