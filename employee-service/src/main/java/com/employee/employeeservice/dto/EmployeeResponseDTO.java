package com.employee.employeeservice.dto;


import lombok.Data;

@Data
public class EmployeeResponseDTO {

    private String employeeId;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String designation;

}
