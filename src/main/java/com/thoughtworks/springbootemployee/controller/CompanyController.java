package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    List<Company> getCompanies() {
        return this.companyService.findAllCompanies();
    }

    @GetMapping("/{id}")
    Company getCompanyById(@PathVariable("id") Integer id) {
        return this.companyService.findCompanyByID(id);
    }

    @GetMapping("/{id}/employees")
    List<Employee> getEmployeesByCompanyId(@PathVariable("id") Integer id) {
        return this.companyService.findEmployeesByCompanyID(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    Page<Company> getCompaniesByPageAndSize(int page, int pageSize) {
        return this.companyService.findCompaniesByPageAndPageSize(--page, pageSize);
    }

    @PostMapping
    Company addCompany(@RequestBody Company company) {
        return this.companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    Company updateCompany(@RequestBody Company newCompany, @PathVariable("id") Integer id) {
        return this.companyService.updateCompany(id, newCompany);
    }

    @DeleteMapping("/{id}")
    void deleteCompany(@PathVariable("id") Integer id) {
        this.companyService.deleteCompanyByID(id);
    }
}
