package com.example.Employeemanagement.Services.Impl;

import com.example.Employeemanagement.Convertors.EmployeeConvertor;
import com.example.Employeemanagement.DTOs.Requests.EmployeeReqDto;
import com.example.Employeemanagement.DTOs.Requests.EmployeeUpdateReqDTO;
import com.example.Employeemanagement.DTOs.Responses.EmployeeResponseDTO;
import com.example.Employeemanagement.Exceptions.DepartmentNotFoundException;
import com.example.Employeemanagement.Exceptions.EmployeeNotFoundException;
import com.example.Employeemanagement.Models.Department;
import com.example.Employeemanagement.Models.Employee;
import com.example.Employeemanagement.Repository.DepartmentRepository;
import com.example.Employeemanagement.Repository.EmployeeRepository;
import com.example.Employeemanagement.Services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createEmployee(EmployeeReqDto employeeReqDto) {

        //getting the department, if department not found then throwing exception
        Optional<Department> departmentOptional = departmentRepository.findById(employeeReqDto.getDepId());
        if(departmentOptional.isEmpty())
            throw new DepartmentNotFoundException("Department not found with given ID");
        Department department = departmentOptional.get();

        //creating employee object and saving in db
        Employee employee = EmployeeConvertor.employeeReqDtoToEmployee(employeeReqDto, department);
        department.getEmployeeList().add(employee);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(int employeeId) {
        //getting the employee, if not found then throwing exception
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty())
            throw new EmployeeNotFoundException("Employee not found");

        Employee employee = employeeOptional.get();
        return EmployeeConvertor.employeeToEmployeeResDto(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(EmployeeUpdateReqDTO employeeUpdateReqDTO) {
        //getting the employee, if not found then throwing exception
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeUpdateReqDTO.getEmployeeId());
        if(employeeOptional.isEmpty())
            throw new EmployeeNotFoundException("Employee not found, Cannot update");

        Employee employee = employeeOptional.get();
        employee.setName(employeeUpdateReqDTO.getName());
        employee.setSalary(employeeUpdateReqDTO.getSalary());
        employee.setJoiningDate(employeeUpdateReqDTO.getJoiningDate());
        employeeRepository.save(employee);

        return EmployeeConvertor.employeeToEmployeeResDto(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void updateDepartment(Integer employeeId, Integer newDepartmentId) {
        //getting the employee, if not found then throwing exception
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty())
            throw new EmployeeNotFoundException("Employee not found, Cannot update");

        //getting the department, if department not found then throwing exception
        Optional<Department> departmentOptional = departmentRepository.findById(newDepartmentId);
        if(departmentOptional.isEmpty())
            throw new DepartmentNotFoundException("Department not found with given ID");

        Department department = departmentOptional.get();
        Employee employee = employeeOptional.get();
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponseDTO getMaxEarningEmployee() {
        Employee employee = employeeRepository.getMaxSalaryEmployee();
        return EmployeeConvertor.employeeToEmployeeResDto(employee);
    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeConvertor::employeeToEmployeeResDto);
    }
}