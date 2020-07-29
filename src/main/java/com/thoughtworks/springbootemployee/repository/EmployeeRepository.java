package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee("1", 18, "female", "eva", 10000));
        employees.add(new Employee("3", 24, "male", "gradle", 12000));
        employees.add(new Employee("2", 24, "male", "java", 15000));
        employees.add(new Employee("4", 24, "male", "java", 15000));
        employees.add(new Employee("5", 24, "male", "java", 15000));
        employees.add(new Employee("6", 24, "male", "java", 15000));
        employees.add(new Employee("7", 24, "male", "java", 15000));
    }

    public List<Employee> findAllEmployees() {
        return this.employees;
    }

    public Employee findEmployeeByID(String employeeId) {
        return null;
    }


    public List<Employee> findEmployeesByPageAndPageSize(int page, int pageSize) {
        return null;
    }
}
