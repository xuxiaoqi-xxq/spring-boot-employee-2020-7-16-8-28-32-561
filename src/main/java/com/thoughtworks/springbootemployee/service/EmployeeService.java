package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee update(String employeeID, Employee updatedEmployee) {
        Employee employee = employeeRepository.findEmployeeByID(employeeID);
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        employee.setSalary(updatedEmployee.getSalary());
        return employee;
    }

    public List<Employee> findAll() {
        return this.employeeRepository.findAllEmployees();
    }

    public List<Employee> findEmployeesByPageAndPageSize(int page, int pageSize) {
        return null;
    }
}
