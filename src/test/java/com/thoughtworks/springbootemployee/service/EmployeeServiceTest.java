package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class EmployeeServiceTest {
    @Test
    void should_update_employee_when_update_given_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        given(employeeRepository.findEmployeeByID("1")).willReturn(new Employee("1",18,"female","chris",9999));

        //when
        Employee updatedEmployee = new Employee("1", 18, "female", "eva", 1000);
        Employee employee = employeeService.update("1", updatedEmployee);

        //then
        assertEquals("1", employee.getId());
        assertEquals("eva", employee.getName());
        assertEquals("female", employee.getGender());
        assertEquals(1000, employee.getSalary());
    }
}
