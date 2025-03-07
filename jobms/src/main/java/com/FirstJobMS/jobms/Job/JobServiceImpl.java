package com.FirstJobMS.jobms.Job;

import com.FirstJobMS.jobms.Job.Dto.JobDTO;
import com.FirstJobMS.jobms.Job.External.Company;
import com.FirstJobMS.jobms.Job.External.Review;
import com.FirstJobMS.jobms.Job.clients.CompanyClient;
import com.FirstJobMS.jobms.Job.clients.ReviewClient;
import com.FirstJobMS.jobms.Job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @Autowired //provide instance of RestTemplate during runtime
    RestTemplate restTemplate;

    int attempt = 0;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;

    public JobDTO ConvertToDTO(Job job) {
/*        List<Job> jobs = repo.findAll();
        List<JobDTO> jobWithCompanyDTOs = new ArrayList<>();
ad
        RestTemplate  restTemplate = new RestTemplate();

        for(Job job : jobs) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJob(job);
*/
//            Company company = restTemplate.getForObject("http://companyms:8081/companies/" + job.getCompanyId(),
//                    Company.class);

        Company company = companyClient.getCompany(job.getCompanyId());

//            ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://reviewms:8083/reviews?companyId=" + job.getCompanyId(),
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<Review>>() {
//
//                    });

//            List<Review> reviews = reviewResponse.getBody();
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

            JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(
                    job, company, reviews);

        //    jobDTO.setCompany(company);

        //    jobWithCompanyDTOs.add(jobDTO);
        // }

        return jobDTO;
    }

    @Override
//    @CircuitBreaker(name = "companyBreaker",
    //    fallbackMethod = "companyBreakerFallback")
    @Retry(name = "companyBreaker",
            fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        System.out.println("attempt: " + ++attempt);
        List<Job> jobs = repo.findAll();
                return jobs.stream()
                        .map(this::ConvertToDTO)
                        .toList();
    }

    public List<String> companyBreaker(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }


    public void createJob(Job job) {
//        job.setId(nextId++);
        repo.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = repo.findById(id).orElse(null);
        return ConvertToDTO(job);

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
