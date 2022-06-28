package com.company.management.controller;

import com.company.management.model.*;
import com.company.management.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/company")
    public ResponseEntity<Company> saveCompany(@Valid @RequestBody Company company) {
        Company company1 = companyService.saveCompany(company);
        return new ResponseEntity<>(company1, HttpStatus.CREATED);
    }

    @PostMapping("/companies")
    public ResponseEntity<List<Company>> saveCompanies(@Valid @RequestBody List<Company> companies) {
        List<Company> companies1 = companyService.saveCompanies(companies);
        return new ResponseEntity<>(companies1, HttpStatus.CREATED);
    }

    @PutMapping("/company")
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company company) {
        Company company1 = companyService.updateCompany(company);
        return new ResponseEntity<>(company1, HttpStatus.OK);
    }

    @PutMapping("/companies")
    public ResponseEntity<List<Company>> updateCompanies(@Valid @RequestBody List<Company> companies) {
        List<Company> companies1 = companyService.updateCompanies(companies);
        return new ResponseEntity<>(companies1, HttpStatus.OK);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable long id) {
        companyService.deleteCompany(id);
        Employee employee = new Employee();
        return new ResponseEntity<>("comapny deleted with id = " + id, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/companies")
    public ResponseEntity<?> deleteCompanies(@RequestParam List<Long> ids) {
        companyService.deleteCompanies(ids);
        return new ResponseEntity<>("companies with id = " + ids + " deleted", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/companies/all")
    public ResponseEntity<?> deleteAllCompanies() {
        companyService.deleteAllCompanies();
        return new ResponseEntity<>("all companies has been deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = companyService.getCompany(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(@RequestParam List<Long> ids) {
        List<Company> companies = companyService.getCompanies(ids);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/companies/all")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/companies/sorted/{fieldName}")
    public ResponseEntity<List<Company>> getCompaniesInSortedOrder(@PathVariable String fieldName) {
        List<Company> companies = companyService.getCompaniesInSortedOrder(fieldName);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/companies/paginated/{pageNumber}/{numberOfRecords}")
    public ResponseEntity<Page<Company>> getCompaniesInPaginatedOrder(@PathVariable int pageNumber,
                                                                      @PathVariable int numberOfRecords) {
        Page<Company> companies =
                companyService.getCompaniesInPaginatedOrder(pageNumber, numberOfRecords);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/companies/sortedPaginated/{pageNumber}/{numberOfRecords}/{fieldName}")
    public ResponseEntity<Page<Company>> getCompaniesInPaginatedSortedOrder(@PathVariable int pageNumber,
                                                                            @PathVariable int numberOfRecords,
                                                                            @PathVariable String fieldName) {
        Page<Company> companies =
                companyService.getCompaniesInPaginatedSortedOrder(pageNumber, numberOfRecords, fieldName);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/companies/count")
    public ResponseEntity<?> totalNumberOfCompanies() {
        Long numberOfCompanies = companyService.totalNumberOfCompanies();
        return new ResponseEntity<>("total companies = " + numberOfCompanies, HttpStatus.OK);
    }
}
