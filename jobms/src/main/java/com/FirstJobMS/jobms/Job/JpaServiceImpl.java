package com.FirstJobMS.jobms.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaServiceImpl implements IJobService {
    //private List<Job> jobs = new ArrayList<>();
    @Autowired
    public JobRepository repo;
    private Long nextId = 1L;

    public List<Job> findAll() {
        return repo.findAll();
    }

    public void createJob(Job job) {
        job.setId(nextId++);
        repo.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = repo.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            return true;
        }
        return false;
    }
}
