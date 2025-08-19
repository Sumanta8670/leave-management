package com.symplora.leavemanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    private String department;

    @Column(nullable=false)
    private LocalDate joiningDate;

    @Column(nullable=false)
    private Integer annualLeaveBalance;

    @Version
    private Integer version;

    // Constructors
    public Employee() {}
    public Employee(String name, String email, String department, LocalDate joiningDate, Integer annualLeaveBalance) {
        this.name = name; this.email = email; this.department = department;
        this.joiningDate = joiningDate; this.annualLeaveBalance = annualLeaveBalance;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
    public Integer getAnnualLeaveBalance() { return annualLeaveBalance; }
    public void setAnnualLeaveBalance(Integer annualLeaveBalance) { this.annualLeaveBalance = annualLeaveBalance; }
    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
}