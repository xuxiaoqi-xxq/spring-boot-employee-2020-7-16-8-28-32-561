package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> findAllCompanies() {
        return this.companyRepository.findAll();
    }

    public Company findCompanyByID(Integer companyID) {
        return this.companyRepository.findById(companyID).orElse(null);
    }

    public List<Employee> findEmployeesByCompanyID(Integer companyID) throws NoSuchDataException {

        Company company = this.companyRepository.findById(companyID).orElse(null);
        if (company == null) {
            throw new NoSuchDataException();
        }
        return company.getEmployees();
    }

    public Page<Company> findCompaniesByPageAndPageSize(int page, int pageSize) {
        return this.companyRepository.findAll(PageRequest.of(page, pageSize));
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
//        Company company = this.companyRepository.findById(companyID).orElse(null);
//        List<Employee> employees = company != null ? company.getEmployees() : null;
        this.companyRepository.deleteById(companyID);
//        if (employees != null) {
//            this.employeeRepository.deleteAll(employees);
//        }
    }
}
