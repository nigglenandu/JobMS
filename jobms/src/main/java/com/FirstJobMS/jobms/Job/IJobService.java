package com.FirstJobMS.jobms.Job;

import com.FirstJobMS.jobms.Job.Dto.JobWithCompanyDTO;

import java.util.List;

public interface IJobService {
    List<JobWithCompanyDTO>  findAll();
    void createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
