package com.FJobMS.companyms.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService{
    @Autowired
    CompanyRepository Repo;

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
}
