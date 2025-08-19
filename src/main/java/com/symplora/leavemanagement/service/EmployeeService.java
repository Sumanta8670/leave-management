package com.symplora.leavemanagement.service;

import com.symplora.leavemanagement.model.Employee;
import com.symplora.leavemanagement.repository.EmployeeRepository;
import com.symplora.leavemanagement.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository empRepo;

    public EmployeeService(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        // optional: check duplicate email
        empRepo.findByEmail(employee.getEmail()).ifPresent(e -> {
            throw new IllegalArgumentException("Email already exists");
        });
        return empRepo.save(employee);
    }

    public Employee getById(Long id) {
        return empRepo.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }
}
