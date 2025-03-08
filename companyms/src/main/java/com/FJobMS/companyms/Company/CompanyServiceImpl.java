package com.FJobMS.companyms.Company;

import com.FJobMS.companyms.Company.clients.ReviewClient;
import com.FJobMS.companyms.Company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService{
    private CompanyRepository Repo;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository repo, ReviewClient reviewClient) {
        this.Repo = repo;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return Repo.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> optional = Repo.findById(id);
        if (optional.isPresent()) {
            Company companyToUpdate = optional.get();
            companyToUpdate.setDescription(company.getDescription());
            Repo.save(companyToUpdate);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        Repo.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (Repo.existsById(id)) {
            Repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return Repo.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());

        Company company = Repo.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company not found" + reviewMessage.getCompanyId()));
        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        Repo.save(company);
        }
}
