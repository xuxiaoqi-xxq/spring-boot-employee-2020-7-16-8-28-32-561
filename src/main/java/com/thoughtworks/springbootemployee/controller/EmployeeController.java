package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        initEmployees();
    }

    private void initEmployees() {
        employees.add(new Employee("1", 18, "female", "eva", 10000));
        employees.add(new Employee("3", 24, "male", "gradle", 12000));
        employees.add(new Employee("2", 24, "male", "java", 15000));
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return this.employees;
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getAllEmployeesByPageAndSize(int page, int size) {
        return this.employees.subList((page - 1) * size, page * size);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getAllEmployeesByGender(String gender) {
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
    Employee addCompany(@RequestBody Employee employee) {
        this.employees.add(employee);
        return employee;
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
