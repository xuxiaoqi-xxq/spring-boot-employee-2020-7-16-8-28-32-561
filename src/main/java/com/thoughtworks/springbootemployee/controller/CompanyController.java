package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final List<Company> companies = new ArrayList<>();

    public CompanyController() {
        initCompanies();
    }

    private void initCompanies() {
        Employee employee1 = new Employee("1", 18, "female", "eva", 10000);
        Employee employee2 = new Employee("2", 24, "male", "java", 15000);
        Employee employee3 = new Employee("3", 24, "male", "gradle", 12000);
        companies.add(new Company("1", "OOCL", Collections.singletonList(employee1)));
        companies.add(new Company("2", "ThoughtWorks", Arrays.asList(employee2, employee3)));
    }

    @GetMapping("/{id}")
    Company getCompanyById(@PathVariable("id") String id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/{id}/employees")
    List<Employee> getEmployeesByCompanyId(@PathVariable("id") String id) {
        return companies.stream()
                .filter(company -> company.getId().equals(id))
                .map(Company::getEmployees)
                .findFirst()
                .orElse(null);
    }

    @GetMapping
    List<Company> getCompaniesByPageAndSize(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "0") int size) {
        if (page == 0 && size == 0) {
            return this.companies;
        }
        return this.companies.subList((page - 1) * size, page * size);
    }

    @PostMapping
    String addCompany(@RequestBody Company company) {
        this.companies.add(company);
        return "success";
    }

    @PutMapping("/{id}")
    String updateCompany(@RequestBody Company newCompany, @PathVariable("id") String id) {
        newCompany.setId(id);
        Company oldCompany = this.companies.stream()
                .filter(company -> company.getId().equals(newCompany.getId()))
                .findFirst().orElse(null);
        if (oldCompany == null) {
            return "fail";
        }
        this.companies.remove(oldCompany);
        this.companies.add(newCompany);
        return "success";
    }

    @DeleteMapping("/{id}")
    String deleteCompany(@PathVariable("id") String id) {
        Company foundCompany = this.companies.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (foundCompany != null) {
            foundCompany.setEmployees(null);
            return "success";
        } else {
            return "fail";
        }
    }
}
