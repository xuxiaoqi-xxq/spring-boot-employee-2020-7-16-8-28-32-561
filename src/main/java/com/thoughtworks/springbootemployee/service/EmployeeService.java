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
        return this.employeeRepository.findEmployeesByPageAndPageSize(page, pageSize);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return this.employeeRepository.findEmployeesByGender(gender);
    }

    public Employee findEmployeeByID(String employeeID) {
        return this.employeeRepository.findEmployeeByID(employeeID);
    }

    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.addEmployee(employee);
    }

    public void deleteEmployeeByID(String employeeID) {
        this.employeeRepository.deleteEmployeeByID(employeeID);
    }
}
