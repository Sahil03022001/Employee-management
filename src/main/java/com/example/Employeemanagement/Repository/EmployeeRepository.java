package com.example.Employeemanagement.Repository;

import com.example.Employeemanagement.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT * FROM employee ORDER BY salary desc limit 1", nativeQuery = true)
    Employee getMaxSalaryEmployee();
}
