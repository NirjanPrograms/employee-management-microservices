package com.employee.employeeservice.repository;

import com.employee.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeId(String employeeId);
    Boolean existsByEmployeeId(String employeeId);
}
