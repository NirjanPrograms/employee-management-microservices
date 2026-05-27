package com.department.department_service.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "departments")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String departmentCode;

    private String departmentName;

    private String departmentDescription;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
