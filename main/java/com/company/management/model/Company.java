package com.company.management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company implements Serializable {
    @Id
    @Column(name = "company_id")
    @Min(value = 1, message = "sorry! company ID should be greater than 1")
    @Max(value = 1000, message = "sorry! company ID should be less than 1000")
    private long companyId;
    @NotBlank(message = "sorry! the name of the company can not be blank")
    @Column(name = "company_name")
    private String companyName;
    @NotBlank(message = "sorry! the location of the company can not be blank")
    @Column(name = "company_location")
    private String companyLocation;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = Employee.class, mappedBy = "company")
    @JsonManagedReference
    private List<Employee> employees;
}
