package com.example.Employeemanagement.DTOs.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReqDto {

    private String name;
    private int salary;
    private Date joiningDate;
    private Integer depId;
}
