package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    void should_return_companies_when_hit_companies_endpoint_given_nothing() throws Exception {
        //given
        List<Company> companies = Collections.singletonList(new Company(1, "oocl", null));
        companyRepository.saveAll(companies);
        //when
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("oocl"));
        //then
    }

    @Test
    void should_page_companies_when_hit_companies_endpoint_given_page_and_pageSize() throws Exception {
        //given
        List<Company> companies = Collections.singletonList(new Company(1, "oocl", null));
        companyRepository.saveAll(companies);
        //when
        mockMvc.perform(get("/companies?page=1&pageSize=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].name").value("oocl"));
        //then
    }

    @Test
    void should_return_created_companies_when_hit_companies_endpoint_given_company() throws Exception {
        //given
        String createdCompany = "{ \n" +
                "    \"name\": \"gdsa\",\n" +
                "    \"employees\": null\n" +
                "}";
        //when
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(createdCompany))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("gdsa"));
        //then
        List<Company> companies = companyRepository.findAll();
        assertEquals("gdsa", companies.get(0).getName());
    }

    @Test
    void should_return_specific_companies_when_hit_companies_endpoint_given_company() throws Exception {
        //given
        Company company = new Company(1, "oocw", null);
        Company savedCompany = companyRepository.save(company);
        //when
        mockMvc.perform(get("/companies/" + savedCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCompany.getId()))
                .andExpect(jsonPath("$.name").value("oocw"));
    }


}
