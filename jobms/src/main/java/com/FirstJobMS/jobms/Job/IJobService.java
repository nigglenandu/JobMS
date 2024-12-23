package com.FirstJobMS.jobms.Job;

import java.util.List;

public interface IJobService {
    List<Job>  findAll();
    void createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
