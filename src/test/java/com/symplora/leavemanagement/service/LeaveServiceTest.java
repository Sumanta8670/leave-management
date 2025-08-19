package com.symplora.leavemanagement.service;

import com.symplora.leavemanagement.dto.ApplyLeaveRequest;
import com.symplora.leavemanagement.model.Employee;
import com.symplora.leavemanagement.model.LeaveRequest;
import com.symplora.leavemanagement.model.LeaveStatus;
import com.symplora.leavemanagement.model.LeaveType;
import com.symplora.leavemanagement.repository.EmployeeRepository;
import com.symplora.leavemanagement.repository.LeaveRequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LeaveServiceTest {

    @Test
    void applyLeaveShouldSucceed() {
        var empRepo = Mockito.mock(EmployeeRepository.class);
        var leaveRepo = Mockito.mock(LeaveRequestRepository.class);

        var service = new LeaveService(leaveRepo, empRepo);

        Employee emp = new Employee("Test", "t@test.com", "Eng", LocalDate.of(2025,1,1), 10);
        emp.setId(1L);

        Mockito.when(empRepo.findById(1L)).thenReturn(Optional.of(emp));
        Mockito.when(leaveRepo.findOverlapping(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(java.util.Collections.emptyList());
        Mockito.when(leaveRepo.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));

        ApplyLeaveRequest req = new ApplyLeaveRequest();
        req.setStartDate(LocalDate.of(2025,1,10));
        req.setEndDate(LocalDate.of(2025,1,11));
        req.setType(LeaveType.CASUAL);

        LeaveRequest lr = service.applyLeave(1L, req);

        assertEquals(LeaveStatus.PENDING, lr.getStatus());
        assertEquals(2, lr.getDays());
    }
}
