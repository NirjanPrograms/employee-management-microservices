package com.department.department_service.repository;

import com.department.department_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentCode(String departmentCode);

    Boolean existsByDepartmentCode(String departmentCode);

    // This looks at the table and returns true if AT LEAST one record exists
    Boolean existsBy();
}
