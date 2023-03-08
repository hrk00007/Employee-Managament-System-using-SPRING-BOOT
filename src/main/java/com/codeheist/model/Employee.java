package com.codeheist.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "empTab")
public class Employee {
    @Id
    @GeneratedValue
    private Integer empId;
    private String empName;
    private String empEmail;
    private Double empSal;
    private String empAddr;
    private String empDept;
    private Double hra;
    private Double da;
}
