package com.symplora.leavemanagement.service;

import com.symplora.leavemanagement.dto.ApplyLeaveRequest;
import com.symplora.leavemanagement.exception.BadRequestException;
import com.symplora.leavemanagement.exception.ConflictException;
import com.symplora.leavemanagement.exception.NotFoundException;
import com.symplora.leavemanagement.model.*;
import com.symplora.leavemanagement.repository.EmployeeRepository;
import com.symplora.leavemanagement.repository.LeaveRequestRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {
    private final LeaveRequestRepository leaveRepo;
    private final EmployeeRepository empRepo;

    public LeaveService(LeaveRequestRepository leaveRepo, EmployeeRepository empRepo) {
        this.leaveRepo = leaveRepo;
        this.empRepo = empRepo;
    }

    public int computeDaysInclusive(LocalDate start, LocalDate end) {
        return (int) ChronoUnit.DAYS.between(start, end) + 1;
    }

    @Transactional
    public LeaveRequest applyLeave(Long empId, ApplyLeaveRequest request) {
        var emp = empRepo.findById(empId).orElseThrow(() -> new NotFoundException("Employee not found"));

        if (request.getStartDate().isBefore(emp.getJoiningDate())) {
            throw new BadRequestException("Start date is before joining date");
        }
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new BadRequestException("End date is before start date");
        }

        int days = computeDaysInclusive(request.getStartDate(), request.getEndDate());
        if (days > emp.getAnnualLeaveBalance()) {
            throw new BadRequestException("Insufficient leave balance");
        }

        List<LeaveStatus> checkStates = List.of(LeaveStatus.PENDING, LeaveStatus.APPROVED);
        var overlaps = leaveRepo.findOverlapping(empId, request.getStartDate(), request.getEndDate(), checkStates);
        if (!overlaps.isEmpty()) throw new ConflictException("Overlapping leave exists");

        LeaveRequest lr = new LeaveRequest(
                emp,
                request.getStartDate(),
                request.getEndDate(),
                days,
                request.getType(),
                LeaveStatus.PENDING,
                request.getNotes()
        );

        return leaveRepo.save(lr);
    }

    @Transactional
    public LeaveRequest approveLeave(Long leaveId, String approver) {
        var lr = leaveRepo.findById(leaveId).orElseThrow(() -> new NotFoundException("Leave not found"));
        if (lr.getStatus() != LeaveStatus.PENDING) throw new BadRequestException("Leave is not pending");

        var emp = lr.getEmployee();
        if (lr.getDays() > emp.getAnnualLeaveBalance()) throw new BadRequestException("Insufficient balance at approval time");

        emp.setAnnualLeaveBalance(emp.getAnnualLeaveBalance() - lr.getDays());
        lr.setStatus(LeaveStatus.APPROVED);
        lr.setApprover(approver);
        lr.setApprovedAt(LocalDateTime.now());

        try {
            empRepo.save(emp);
            return leaveRepo.save(lr);
        } catch (OptimisticLockingFailureException ex) {
            throw new ConflictException("Concurrent modification, please retry");
        }
    }

    @Transactional
    public LeaveRequest rejectLeave(Long leaveId, String approver) {
        var lr = leaveRepo.findById(leaveId).orElseThrow(() -> new NotFoundException("Leave not found"));
        if (lr.getStatus() != LeaveStatus.PENDING) throw new BadRequestException("Leave is not pending");

        lr.setStatus(LeaveStatus.REJECTED);
        lr.setApprover(approver);
        lr.setApprovedAt(LocalDateTime.now());

        return leaveRepo.save(lr);
    }

    public List<LeaveRequest> getLeavesByEmployee(Long empId) {
        var employee = empRepo.findById(empId)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
        return leaveRepo.findByEmployee(employee);
    }
}
