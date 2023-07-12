package com.example.Employeemanagement.Services.Impl;

import com.example.Employeemanagement.Models.Department;
import com.example.Employeemanagement.Repository.DepartmentRepository;
import com.example.Employeemanagement.Services.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createDepartment(String departmentName) {
        Department department = new Department();
        department.setName(departmentName);
        departmentRepository.save(department);
    }
}
