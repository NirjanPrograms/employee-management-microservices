package com.employee.employeeservice.controller;


import com.employee.employeeservice.dto.EmployeeRequestDTO;
import com.employee.employeeservice.dto.EmployeeResponseDTO;
import com.employee.employeeservice.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO requestDTO){
        return employeeService.saveEmployee(requestDTO);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Optional<EmployeeResponseDTO> getEmployeeByEmployeeId(@PathVariable String employeeId){
        return employeeService.getEmployeeByEmployeeId(employeeId);
    }

    @PutMapping("/{employeeId}")
    public EmployeeResponseDTO updateEmployee(@PathVariable String employeeId,@RequestBody EmployeeRequestDTO requestDTO){
        return employeeService.updateEmployee(employeeId, requestDTO);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

}
