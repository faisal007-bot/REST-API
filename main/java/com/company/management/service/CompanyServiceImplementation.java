package com.company.management.service;

import com.company.management.exception.CompanyNotFoundException;
import com.company.management.model.Company;
import com.company.management.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImplementation implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company saveCompany(Company company) {
        Company company1 = companyRepository.saveAndFlush(company);
        return company1;
    }

    @Override
    public List<Company> saveCompanies(List<Company> companies) {
        List<Company> companies1 = companyRepository.saveAllAndFlush(companies);
        return companies1;
    }

    @Override
    public Company updateCompany(Company company) {
        Company company1 = companyRepository.save(company);
        return company1;
    }

    @Override
    public List<Company> updateCompanies(List<Company> companies) {
        List<Company> companies1 = companyRepository.saveAllAndFlush(companies);
        return companies1;
    }

    @Override
    public void deleteCompany(Long companyId) {
        List<Company> companies = companyRepository.findAll();
        boolean isValid = companies.stream()
                .anyMatch(c -> c.getCompanyId() == companyId);
        if (isValid)
            companyRepository.deleteById(companyId);
        else
            throw new CompanyNotFoundException("sorry the company with the provided id: "
                    + companyId + " does not exist");
    }

    @Override
    public void deleteCompanies(List<Long> ids) {
        companyRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void deleteAllCompanies() {
        companyRepository.deleteAllInBatch();
    }

    @Override
    public Company getCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).
                orElseThrow(() -> new CompanyNotFoundException("sorry the company with the id:" + companyId
                        + " is not present"));
        return company;
    }

    @Override
    public List<Company> getCompanies(List<Long> ids) {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    @Override
    public List<Company> getCompaniesInSortedOrder(String fieldName) {
        List<Company> companies =
                companyRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
        return companies;
    }

    @Override
    public Page<Company> getCompaniesInPaginatedOrder(int pageNumber, int numberOfRecords) {
        Page<Company> companies =
                companyRepository.findAll(PageRequest.of(pageNumber, numberOfRecords));
        return companies;
    }

    @Override
    public Page<Company> getCompaniesInPaginatedSortedOrder
            (int pageNumber, int numberOfRecords, String fieldName) {
        Page<Company> companies = companyRepository.findAll(PageRequest.of
                (pageNumber, numberOfRecords, Sort.by(Sort.Direction.ASC, fieldName)));
        return companies;
    }

    @Override
    public Long totalNumberOfCompanies() {
        long count = companyRepository.count();
        return count;
    }
}
