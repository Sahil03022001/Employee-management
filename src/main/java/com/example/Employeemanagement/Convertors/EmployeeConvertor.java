package com.example.Employeemanagement.Convertors;

import com.example.Employeemanagement.DTOs.Requests.EmployeeReqDto;
import com.example.Employeemanagement.DTOs.Responses.EmployeeResponseDTO;
import com.example.Employeemanagement.Models.Department;
import com.example.Employeemanagement.Models.Employee;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeConvertor {

    public static Employee employeeReqDtoToEmployee(EmployeeReqDto employeeReqDto, Department department) {
        return Employee.builder()
                .name(employeeReqDto.getName())
                .salary(employeeReqDto.getSalary())
                .joiningDate(employeeReqDto.getJoiningDate())
                .department(department)
                .build();
    }

    public static EmployeeResponseDTO employeeToEmployeeResDto(Employee employee) {
        return EmployeeResponseDTO.builder()
                .name(employee.getName())
                .salary(employee.getSalary())
                .joiningDate(employee.getJoiningDate())
                .departmentName(employee.getDepartment().getName())
                .build();
    }
}