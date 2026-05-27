package com.department.department_service.controller;


import com.department.department_service.dto.DepartmentRequestDTO;
import com.department.department_service.dto.DepartmentResponseDTO;
import com.department.department_service.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    private DepartmentController (DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO requestDTO){
        return departmentService.saveDepartment(requestDTO);
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentCode}")
    public Optional<DepartmentResponseDTO> getDepartmentsByDepartmentCode(@PathVariable String departmentCode){
        return departmentService.getDepartmentsByDepartmentCode(departmentCode);
    }

    @PutMapping("/{departmentCode}")
    public DepartmentResponseDTO updateDepartment(@PathVariable String departmentCode,@RequestBody DepartmentRequestDTO requestDTO){
        return departmentService.updateDepartment(departmentCode, requestDTO);
    }

    @DeleteMapping("/{departmentCode}")
    public String deleteDepartment(@PathVariable String departmentCode){
        return departmentService.deleteDepartment(departmentCode);
    }
}
