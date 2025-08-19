package com.symplora.leavemanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_request")
public class LeaveRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable=false)
    private LocalDate startDate;

    @Column(nullable=false)
    private LocalDate endDate;

    @Column(nullable=false)
    private Integer days;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private LeaveType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private LeaveStatus status;

    private LocalDateTime appliedAt;
    private LocalDateTime approvedAt;
    private String approver;
    private String notes;

    public LeaveRequest() {}

    // Constructors, getters, setters
    public LeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate, Integer days, LeaveType type, LeaveStatus status, String notes) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.type = type;
        this.status = status;
        this.notes = notes;
        this.appliedAt = LocalDateTime.now();
    }

    // Getters & setters (generate in IDE)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Integer getDays() { return days; }
    public void setDays(Integer days) { this.days = days; }
    public LeaveType getType() { return type; }
    public void setType(LeaveType type) { this.type = type; }
    public LeaveStatus getStatus() { return status; }
    public void setStatus(LeaveStatus status) { this.status = status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
    public String getApprover() { return approver; }
    public void setApprover(String approver) { this.approver = approver; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
