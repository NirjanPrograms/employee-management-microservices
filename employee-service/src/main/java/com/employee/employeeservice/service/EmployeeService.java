package com.employee.employeeservice.service;


import com.employee.employeeservice.dto.EmployeeRequestDTO;
import com.employee.employeeservice.dto.EmployeeResponseDTO;
import com.employee.employeeservice.exception.ResourceNotFoundException;
import com.employee.employeeservice.model.Employee;
import com.employee.employeeservice.repository.EmployeeRepository;
import com.employee.employeeservice.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee){
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setPhone(employee.getPhone());
        employeeResponseDTO.setEmail(employee.getEmail());
        employeeResponseDTO.setEmployeeId(employee.getEmployeeId());
        employeeResponseDTO.setDepartment(employee.getDepartment());
        employeeResponseDTO.setDesignation(employee.getDesignation());

        return employeeResponseDTO;
    }

    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO){

        if(employeeRepository.existsByEmployeeId(employeeRequestDTO.getEmployeeId())){
            throw new DuplicateResourceException(
                    "Employee Id already exists." + employeeRequestDTO.getEmployeeId()
            );
        }

        Employee employee = new Employee();
        employee.setEmployeeId(employeeRequestDTO.getEmployeeId());
        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setDesignation(employeeRequestDTO.getDesignation());
        employee.setSalary(employeeRequestDTO.getSalary());
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());

        Employee savedEmployee = employeeRepository.save(employee);

        return toEmployeeResponseDTO(savedEmployee);
    }


    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(this::toEmployeeResponseDTO)
                .toList();
    }

    public Optional<EmployeeResponseDTO> getEmployeeByEmployeeId(String employeeId){
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return Optional.ofNullable(toEmployeeResponseDTO(employee));
    }

    public EmployeeResponseDTO updateEmployee(String employeeId, EmployeeRequestDTO employeeRequestDTO){
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        // Employee I'd can not be updated
        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setDesignation(employeeRequestDTO.getDesignation());
        employee.setSalary(employeeRequestDTO.getSalary());
        employee.setUpdatedAt(LocalDateTime.now());

        Employee savedEmployee = employeeRepository.save(employee);

        return toEmployeeResponseDTO(savedEmployee);
    }

    public String deleteEmployee(String employeeId){
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        employeeRepository.delete(employee);
        return "Employee deleted successfully with id: " + employeeId;
    }
}
