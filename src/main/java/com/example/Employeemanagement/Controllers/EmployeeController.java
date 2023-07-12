package com.example.Employeemanagement.Controllers;

import com.example.Employeemanagement.DTOs.Requests.EmployeeReqDto;
import com.example.Employeemanagement.DTOs.Requests.EmployeeUpdateReqDTO;
import com.example.Employeemanagement.DTOs.Responses.EmployeeResponseDTO;
import com.example.Employeemanagement.Services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeReqDto employeeReqDto) {
        try {
            employeeService.createEmployee(employeeReqDto);
            return new ResponseEntity<>("Employee Added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") int employeeId) {
        try {
            EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployeeById(@RequestBody EmployeeUpdateReqDTO employeeUpdateReqDTO) {
        try {
            EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployeeById(employeeUpdateReqDTO);
            return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable("id") int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping("/update-department")
    public ResponseEntity<String> updateDepartmentOfEmployee(@RequestParam("eid") Integer employeeId,
                                                     @RequestParam("depId") Integer newDepId) {
        try {
            employeeService.updateDepartment(employeeId, newDepId);
            return new ResponseEntity<>("Department Updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/max-earning-employee")
    public EmployeeResponseDTO getMaxEarningEmployee() {
        return employeeService.getMaxEarningEmployee();
    }

    @GetMapping("/get-all-employees")
    public Page<EmployeeResponseDTO> getAllEmployees(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        return employeeService.getAllEmployees(pageNumber, pageSize);
    }
}
