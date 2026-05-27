package com.department.department_service.service;

import com.department.department_service.dto.DepartmentRequestDTO;
import com.department.department_service.dto.DepartmentResponseDTO;
import com.department.department_service.exception.DuplicateResourceException;
import com.department.department_service.exception.ResourceNotFoundException;
import com.department.department_service.model.Department;
import com.department.department_service.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public DepartmentResponseDTO toDepartmentResponseDTO(Department department){
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setDepartmentCode(department.getDepartmentCode());
        departmentResponseDTO.setDepartmentName(department.getDepartmentName());
        departmentResponseDTO.setDepartmentDescription(department.getDepartmentDescription());

        return departmentResponseDTO;
    }

    public DepartmentResponseDTO saveDepartment(DepartmentRequestDTO requestDTO){

        if(departmentRepository.existsByDepartmentCode(requestDTO.getDepartmentCode())){
            throw new DuplicateResourceException(
                    "Department Code already exists. " +requestDTO.getDepartmentCode()
            );
        }

        Department department = new Department();

        department.setDepartmentCode(requestDTO.getDepartmentCode());
        department.setDepartmentName(requestDTO.getDepartmentName());
        department.setDepartmentDescription(requestDTO.getDepartmentDescription());
        department.setCreatedAt(LocalDateTime.now());
        department.setUpdatedAt(LocalDateTime.now());

        Department savedDepartment = departmentRepository.save(department);
        return toDepartmentResponseDTO(savedDepartment);
    }

    public List<DepartmentResponseDTO> getAllDepartments(){

        // If existsBy() is false, the table is empty
        if(!departmentRepository.existsBy()){
            throw new ResourceNotFoundException("Department Not Found");
        }

        return departmentRepository.findAll()
                .stream()
                .map(this::toDepartmentResponseDTO)
                .toList();
    }

    public Optional<DepartmentResponseDTO> getDepartmentsByDepartmentCode(String departmentCode){
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found with code: " + departmentCode));
        return Optional.ofNullable(toDepartmentResponseDTO(department));
    }

    public DepartmentResponseDTO updateDepartment(String departmentCode, DepartmentRequestDTO requestDTO){
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found with code: " + departmentCode));

        department.setDepartmentName(requestDTO.getDepartmentName());
        department.setDepartmentDescription(requestDTO.getDepartmentDescription());
        department.setUpdatedAt(LocalDateTime.now());

        Department savedDepartment = departmentRepository.save(department);
        return toDepartmentResponseDTO(savedDepartment);
    }

    public String deleteDepartment(String departmentCode){
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found with code: " + departmentCode));

        departmentRepository.delete(department);
        return "Department deleted successfully with code " + departmentCode;
    }

}
