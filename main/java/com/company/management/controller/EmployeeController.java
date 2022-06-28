package com.company.management.controller;

import com.company.management.model.Employee;
import com.company.management.service.EmployeeService;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        Employee employee1 = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }

    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> saveEmployees(@Valid @RequestBody List<Employee> employees) {
        List<Employee> employees1 = employeeService.saveEmployees(employees);
        return new ResponseEntity<>(employees1, HttpStatus.CREATED);
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
        Employee employee1 = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<List<Employee>> updateEmployees(@Valid @RequestBody List<Employee> employees) {
        List<Employee> employees1 = employeeService.updateEmployees(employees);
        return new ResponseEntity<>(employees1, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("employee deleted with id = " + id, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<?> deleteEmployees(@RequestParam List<Long> ids) {
        employeeService.deleteEmployees(ids);
        return new ResponseEntity<>("employees with id = " + ids + " deleted", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees/all")
    public ResponseEntity<?> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return new ResponseEntity<>("all employees has been deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam List<Long> ids) {
        List<Employee> employees = employeeService.getEmployees(ids);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("/employees/sorted/{fieldName}")
    public ResponseEntity<List<Employee>> getEmployeesInSortedOrder(@PathVariable String fieldName) {
        List<Employee> employees = employeeService.getEmployeesInSortedOrder(fieldName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/paginated/{pageNumber}/{numberOfRecords}")
    public ResponseEntity<Page<Employee>> getEmployeesInPaginatedOrder(@PathVariable int pageNumber,
                                                                       @PathVariable int numberOfRecords) {
        Page<Employee> employees =
                employeeService.getEmployeesInPaginatedOrder(pageNumber, numberOfRecords);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/sortedPaginated/{pageNumber}/{numberOfRecords}/{fieldName}")
    public ResponseEntity<Page<Employee>> getEmployeesInPaginatedSortedOrder(@PathVariable int pageNumber,
                                                                             @PathVariable int numberOfRecords,
                                                                             @PathVariable String fieldName) {
        Page<Employee> employees =
                employeeService.getEmployeesInPaginatedSortedOrder(pageNumber, numberOfRecords, fieldName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/count")
    public ResponseEntity<?> totalNumberOfEmployees() {
        Long numberOfEmployees = employeeService.totalNumberOfEmployees();
        return new ResponseEntity<>("total employees = " + numberOfEmployees, HttpStatus.OK);
    }
}
