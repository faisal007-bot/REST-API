package com.company.management.service;

import com.company.management.exception.EmployeeNotFoundException;
import com.company.management.model.Employee;
import com.company.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee employee1 = employeeRepository.saveAndFlush(employee);
        return employee1;
    }

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        List<Employee> employees1 = employeeRepository.saveAllAndFlush(employees);
        return employees1;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee employee1 = employeeRepository.saveAndFlush(employee);
        return employee1;
    }

    @Override
    public List<Employee> updateEmployees(List<Employee> employees) {
        List<Employee> employees1 = employeeRepository.saveAllAndFlush(employees);
        return employees1;
    }

    @Override
    public void deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
        List<Employee> employees = employeeRepository.findAll();
        boolean isValid = employees.stream().anyMatch(e -> e.getEmployeeId() == employeeId);
        if (isValid)
            employeeRepository.deleteById(employeeId);
        else
            throw new EmployeeNotFoundException("sorry the employee with employee ID:" + employeeId
                    + " does not exist in the database");
    }

    @Override
    public void deleteEmployees(List<Long> ids) throws EmployeeNotFoundException {
        employeeRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void deleteAllEmployees() throws EmployeeNotFoundException {
        employeeRepository.deleteAllInBatch();
    }

    @Override
    public Employee getEmployee(Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new EmployeeNotFoundException("sorry the employee with id :" +
                        employeeId + " not present in the database"));
        return employee;
    }

    @Override
    public List<Employee> getEmployees(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesInSortedOrder(String fieldName) {
        List<Employee> employees =
                employeeRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
        return employees;
    }

    @Override
    public Page<Employee> getEmployeesInPaginatedOrder(int pageNumber, int numberOfRecords) {
        Page<Employee> employees =
                employeeRepository.findAll(PageRequest.of(pageNumber, numberOfRecords));
        return employees;
    }

    @Override
    public Page<Employee> getEmployeesInPaginatedSortedOrder
            (int pageNumber, int numberOfRecords, String fieldName) {
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of
                (pageNumber, numberOfRecords, Sort.by(Sort.Direction.ASC, fieldName)));
        return employees;
    }

    @Override
    public Long totalNumberOfEmployees() {
        long count = employeeRepository.count();
        return count;
    }
}
