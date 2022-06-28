package com.company.management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {
    @Id
    @Column(name = "employee_id")
    @Min(value = 1, message = "sorry! employee ID should be greater than 1")
    @Max(value = 1000, message = "sorry! employee ID should be less than 1000")
    private long employeeId;
    @Column(name = "employee_name")
    @NotBlank(message = "sorry! the name of the employee can not be blank")
    private String employeeName;
    @Column(name = "employee_city")
    @NotBlank(message = "sorry! the city of the employee can not be blank")
    private String employeeCity;
    @Column(name = "employee_salary")
    @NotNull(message = "sorry! salary of the employee can not be null")
    @Min(value = 1000, message = "sorry! salary should be greater than 1000")
    @Max(value = 1000000, message = "sorry! salary should be less than 1000000")
    private Long employeeSalary;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "sorry! the gender of the employee can not be blank")
    private Gender gender;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = Company.class)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @JsonBackReference
    private Company company;
}
