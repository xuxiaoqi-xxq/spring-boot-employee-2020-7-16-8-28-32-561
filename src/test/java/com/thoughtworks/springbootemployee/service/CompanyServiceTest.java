package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class CompanyServiceTest {

    @Test
    void should_return_all_companies_when_find_all_given() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        List<Company> companies = Arrays.asList(new Company("1", "OOCL", null));
        given(companyRepository.findAllCompanies()).willReturn(companies);

        //when
        List<Company> allCompanies = companyService.findAllCompanies();

        //then
        assertEquals(companies, allCompanies);
    }

    @Test
    void should_return_specific_company_when_find_by_id_given_id() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);
        Company ooclCompany = new Company("1", "OOCL", null);
        given(companyRepository.findCompanyByID("1")).willReturn(ooclCompany);
        //when
        Company company = companyService.findCompanyByID("1");
        //then
        assertEquals(ooclCompany,company);
    }
}
