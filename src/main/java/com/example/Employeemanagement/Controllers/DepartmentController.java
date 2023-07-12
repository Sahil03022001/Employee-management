package com.example.Employeemanagement.Controllers;

import com.example.Employeemanagement.Services.DepartmentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create/{name}")
    public void createDepartment(@PathVariable("name") String departmentName) {
        departmentService.createDepartment(departmentName);
    }
}
