package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee("1", 18, "female", "eva", 10000));
        employees.add(new Employee("3", 24, "male", "gradle", 12000));
    }

    public List<Employee> findAllEmployees() {
        return this.employees;
    }

    public Employee findEmployeeByID(String employeeId) {
        return null;
    }


    public List<Employee> findEmployeesByPageAndPageSize(int page, int pageSize) {
        return this.employees.subList((page - 1) * pageSize, page * pageSize);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return null;
    }

    public Employee addEmployee(Employee employee) {
        return null;
    }

    public void deleteEmployeeByID(String employeeID) {

    }
}
