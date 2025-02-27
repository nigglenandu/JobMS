package com.FirstJobMS.jobms.Job;

import com.FirstJobMS.jobms.Job.Dto.JobWithCompanyDTO;
import com.FirstJobMS.jobms.Job.External.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements IJobService {
    //private List<Job> jobs = new ArrayList<>();
    @Autowired
    public JobRepository repo;
    private Long nextId = 1L;

    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = repo.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

        RestTemplate  restTemplate = new RestTemplate();

        for(Job job : jobs) {
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);

            Company company = restTemplate.getForObject("http://localhost:8081/companies" + job.getCompanyId(),
                    Company.class);

            jobWithCompanyDTO.setCompany(company);

            jobWithCompanyDTOs.add(jobWithCompanyDTO);
        }

        return jobWithCompanyDTOs;
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
