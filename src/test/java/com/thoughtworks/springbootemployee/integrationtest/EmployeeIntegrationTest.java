package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_specific_employees_when_hit_employees_endpoint_given_employee_id() throws Exception {
        //given
        Employee savedEmployee = employeeRepository.save(new Employee(1, 18, "female", "xxx", 10000));

        mockMvc.perform(get("/employees/" + savedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("xxx"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.salary").value(10000))
                .andExpect(jsonPath("$.gender").value("female"));
    }


    @Test
    void should_return_all_employees_when_hit_employees_endpoint_given_nothing() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(new Employee(1, 18, "male", "xxx", 1000));
        employeeRepository.saveAll(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("xxx"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(1000))
                .andExpect(jsonPath("$[0].gender").value("male"));

    }

    @Test
    void should_page_employees_when_hit_employees_endpoint_given_page_and_pageSize() throws Exception {
        //given
        List<Employee> employees = Collections.singletonList(new Employee(1, 18, "male", "xxx", 1000));
        employeeRepository.saveAll(employees);

        mockMvc.perform(get("/employees?page=1&pageSize=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].name").value("xxx"))
                .andExpect(jsonPath("$.content[0].age").value(18))
                .andExpect(jsonPath("$.content[0].salary").value(1000))
                .andExpect(jsonPath("$.content[0].gender").value("male"));
    }

    @Test
    void should_gender_employees_when_hit_employees_endpoint_given_gender() throws Exception {
        //given
        List<Employee> employees = Arrays.asList(new Employee(1, 18, "male", "xxx", 1000),
                new Employee(2, 18, "female", "xxx", 10000));
        employeeRepository.saveAll(employees);

        mockMvc.perform(get("/employees?gender=female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("xxx"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(10000))
                .andExpect(jsonPath("$[0].gender").value("female"));
    }


}
