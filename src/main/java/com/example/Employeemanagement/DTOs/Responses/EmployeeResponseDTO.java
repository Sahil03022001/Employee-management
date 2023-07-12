package com.example.Employeemanagement.DTOs.Responses;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {

    private String name;
    private int salary;
    private Date joiningDate;
    private String departmentName;
}
