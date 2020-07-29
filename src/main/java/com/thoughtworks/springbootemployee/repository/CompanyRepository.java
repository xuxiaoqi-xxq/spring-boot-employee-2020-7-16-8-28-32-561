package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class CompanyRepository {
    public List<Company> findAllCompanies() {
        return null;
    }

    public Company findCompanyByID(Integer companyID) {
        return null;
    }

    public List<Employee> findEmployeesByCompanyID(String companyID) {
        return null;
    }

    public List<Company> findCompaniesByPageAndPageSize(int page, int pageSize) {
        return null;
    }

    public Company addCompany(Company company) {
        return null;
    }

    public Company updateCompany(Company company) {
        return null;
    }
}
