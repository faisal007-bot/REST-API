package com.company.management.service;

import com.company.management.model.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);

    List<Company> saveCompanies(List<Company> companies);

    Company updateCompany(Company company);

    List<Company> updateCompanies(List<Company> companies);

    void deleteCompany(Long companyId);

    void deleteCompanies(List<Long> ids);

    void deleteAllCompanies();

    Company getCompany(Long companyId);

    List<Company> getCompanies(List<Long> ids);

    List<Company> getAllCompanies();

    List<Company> getCompaniesInSortedOrder(String fieldName);

    Page<Company> getCompaniesInPaginatedOrder(int pageNumber, int numberOfRecords);

    Page<Company> getCompaniesInPaginatedSortedOrder
            (int pageNumber, int numberOfRecords, String fieldName);

    Long totalNumberOfCompanies();
}
