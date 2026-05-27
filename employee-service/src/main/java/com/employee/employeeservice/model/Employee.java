package com.employee.employeeservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String employeeId;

    private String name;

    private String email;

    private String phone;

    private String department;

    private String designation;

    private Double salary;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
