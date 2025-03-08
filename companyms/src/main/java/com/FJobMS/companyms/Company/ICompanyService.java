package com.FJobMS.companyms.Company;

import com.FJobMS.companyms.Company.dto.ReviewMessage;

import java.util.List;


public interface ICompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
    public void updateCompanyRating(ReviewMessage reviewMessage);
}
