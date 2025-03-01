package com.FirstJobMS.jobms.Job;

import com.FirstJobMS.jobms.Job.Dto.JobDTO;

import java.util.List;

public interface IJobService {
    List<JobDTO>  findAll();
    void createJob(Job job);
    JobDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
