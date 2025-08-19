package com.symplora.leavemanagement.controller;

import com.symplora.leavemanagement.dto.CreateEmployeeRequest;
import com.symplora.leavemanagement.model.Employee;
import com.symplora.leavemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService empService;
    public EmployeeController(EmployeeService empService) { this.empService = empService; }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody CreateEmployeeRequest req) {
        Employee emp = new Employee(req.getName(), req.getEmail(), req.getDepartment(), req.getJoiningDate(), req.getAnnualLeaveBalance());
        var saved = empService.createEmployee(emp);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> getBalance(@PathVariable Long id) {
        var emp = empService.getById(id);
        var resp = java.util.Map.of(
                "employeeId", emp.getId(),
                "name", emp.getName(),
                "annualLeaveBalance", emp.getAnnualLeaveBalance()
        );
        return ResponseEntity.ok(resp);
    }
}
