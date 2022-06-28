package com.company.management.service;

import com.company.management.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    Employee updateEmployee(Employee employee);

    List<Employee> updateEmployees(List<Employee> employees);

    void deleteEmployee(Long employeeId);

    void deleteEmployees(List<Long> ids);

    void deleteAllEmployees();

    Employee getEmployee(Long employeeId);

    List<Employee> getEmployees(List<Long> ids);

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesInSortedOrder(String fieldName);

    Page<Employee> getEmployeesInPaginatedOrder(int pageNumber, int numberOfRecords);

    Page<Employee> getEmployeesInPaginatedSortedOrder
            (int pageNumber, int numberOfRecords, String fieldName);

    Long totalNumberOfEmployees();
}
