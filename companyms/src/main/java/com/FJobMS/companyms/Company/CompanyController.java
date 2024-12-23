package com.FJobMS.companyms.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private ICompanyService service;

    @GetMapping("companies")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(service.getAllCompanies(),
                HttpStatus.OK);
    }

    @PutMapping("companies/{id}")
    public ResponseEntity<String>  updateCompany(@PathVariable Long id,
                                                 @RequestBody Company company){
        service.updateCompany(company, id);
        return new ResponseEntity<>("Company updated successfully",
                HttpStatus.OK);
    }

    @PostMapping("saveCompany")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        service.createCompany(company);
        return new ResponseEntity<>("Company added successfully",
                HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean isDeleted = service.deleteCompanyById(id);
        if(isDeleted){
            return new ResponseEntity<>("Company Successfully Deleted",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company Not Found",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = service.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}