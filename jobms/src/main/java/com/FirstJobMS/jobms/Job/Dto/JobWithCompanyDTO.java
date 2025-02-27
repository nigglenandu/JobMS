package com.FirstJobMS.jobms.Job.Dto;

import com.FirstJobMS.jobms.Job.External.Company;
import com.FirstJobMS.jobms.Job.Job;

public class JobWithCompanyDTO {
    private Job job;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
