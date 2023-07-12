package com.example.Employeemanagement.Services;

import com.example.Employeemanagement.DTOs.Requests.EmployeeReqDto;
import com.example.Employeemanagement.DTOs.Requests.EmployeeUpdateReqDTO;
import com.example.Employeemanagement.DTOs.Responses.EmployeeResponseDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    void createEmployee(EmployeeReqDto employeeReqDto);

    EmployeeResponseDTO getEmployeeById(int employeeId);
    EmployeeResponseDTO updateEmployeeById(EmployeeUpdateReqDTO employeeUpdateReqDTO);

    void deleteEmployeeById(int employeeId);

    void updateDepartment(Integer employeeId, Integer newDepartmentId);

    EmployeeResponseDTO getMaxEarningEmployee();

    Page<EmployeeResponseDTO> getAllEmployees(int pageNumber, int pageSize);
}
