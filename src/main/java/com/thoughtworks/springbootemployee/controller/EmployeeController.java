package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService ;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService=employeeService;
    }


    @GetMapping()
    public List<Employee> getAllEmployees() {
        return this.employeeService.findAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getAllEmployeesByPageAndSize(Integer page, Integer pageSize) {
        return this.employeeService.findEmployeesByPageAndPageSize(--page,pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getAllEmployeesByGender(String gender) {
        return this.employeeService.findEmployeesByGender(gender);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return this.employeeService.findEmployeeByID(id);
    }

    @PostMapping

    Employee addEmployee(@RequestBody Employee employee) {
        return this.employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable("id") Integer id) throws NoSuchDataException, IllegalOperationException {
        return this.employeeService.update(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable("id") Integer id) {
        this.employeeService.deleteEmployeeByID(id);
    }

}
