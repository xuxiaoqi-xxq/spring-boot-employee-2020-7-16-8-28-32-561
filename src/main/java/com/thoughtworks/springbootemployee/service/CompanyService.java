package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

public class CompanyService {


    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return this.companyRepository.findAllCompanies();
    }

    public Company findCompanyByID(Integer companyID) {
        return this.companyRepository.findCompanyByID(companyID);
    }

    public List<Employee> findEmployeesByCompanyID(String companyID) {
        return this.companyRepository.findEmployeesByCompanyID(companyID);
    }

    public List<Company> findCompaniesByPageAndPageSize(int page, int pageSize) {
        return this.companyRepository.findCompaniesByPageAndPageSize(page,pageSize);
    }

    public Company addCompany(Company company) {
        return this.companyRepository.addCompany(company);
    }

    public Company updateCompany(int companyID, Company newCompany) {
        Company company = this.companyRepository.findCompanyByID(companyID);
        if(newCompany!=null){
            if(newCompany.getName()!=null){
                company.setName(newCompany.getName());
            }
        }
        return company;
    }

    public void deleteCompanyByID(int companyID) {
        this.companyRepository.deleteCompanyByID(companyID);
    }
}
