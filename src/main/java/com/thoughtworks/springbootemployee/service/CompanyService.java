package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class CompanyService {


    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return this.companyRepository.findAll();
    }

    public Company findCompanyByID(Integer companyID) {
        return this.companyRepository.findById(companyID).orElse(null);
    }

    public List<Employee> findEmployeesByCompanyID(Integer companyID) {
        return this.companyRepository.findById(companyID).orElse(null).getEmployees();
    }

    public Page<Company> findCompaniesByPageAndPageSize(int page, int pageSize) {
        return this.companyRepository.findAll(PageRequest.of(page,pageSize));
    }

    public Company addCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public Company updateCompany(int companyID, Company newCompany) {
        Company company = this.companyRepository.findById(companyID).orElse(null);
        if(newCompany!=null){
            if(newCompany.getName()!=null){
                company.setName(newCompany.getName());
            }
            this.companyRepository.save(company);
        }

        return company;
    }

    public void deleteCompanyByID(int companyID) {
        this.companyRepository.deleteById(companyID);
    }
}
