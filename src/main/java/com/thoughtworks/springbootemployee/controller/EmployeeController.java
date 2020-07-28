package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        initEmployees();
    }

    private void initEmployees() {
        employees.add(new Employee("1", 18, "female", "eva", 10000));
        employees.add(new Employee("3", 24, "male", "gradle", 12000));
        employees.add(new Employee("2", 24, "male", "java", 15000));
    }

    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "0") int size, @RequestParam(value ="gender", defaultValue = "null") String gender) {
        if (gender.equals("null")) {
            if (page == 0 && size == 0) {
                return this.employees;
            }
            return this.employees.subList((page - 1) * size, page * size);
        }
        return this.employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") String id) {
        return this.employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    String addCompany(@RequestBody Employee employee) {
        this.employees.add(employee);
        return "success";
    }

    @PutMapping("/{id}")
    String updateCompany(@RequestBody Employee newEmployee, @PathVariable("id") String id) {
        newEmployee.setId(id);
        Employee oldEmployee = this.employees.stream()
                .filter(employee -> employee.getId().equals(newEmployee.getId()))
                .findFirst().orElse(null);
        if (oldEmployee == null) {
            return "fail";
        }
        this.employees.remove(oldEmployee);
        this.employees.add(newEmployee);
        return "success";
    }

    @DeleteMapping("/{id}")
    String deleteCompany(@PathVariable("id") String id) {
        Employee foundEmployee = this.employees.stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (foundEmployee != null) {
            this.employees.remove(foundEmployee);
            return "success";
        } else {
            return "fail";
        }
    }

}
