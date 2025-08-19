package com.symplora.leavemanagement.controller;

import com.symplora.leavemanagement.dto.ApplyLeaveRequest;
import com.symplora.leavemanagement.dto.ApproveLeaveRequest;
import com.symplora.leavemanagement.model.LeaveRequest;
import com.symplora.leavemanagement.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LeaveController {
    private final LeaveService leaveService;
    public LeaveController(LeaveService leaveService) { this.leaveService = leaveService; }

    @PostMapping("/employees/{empId}/leaves")
    public ResponseEntity<LeaveRequest> applyLeave(@PathVariable Long empId, @Valid @RequestBody ApplyLeaveRequest request) {
        var lr = leaveService.applyLeave(empId, request);
        return ResponseEntity.status(201).body(lr);
    }

    @PostMapping("/leaves/{leaveId}/approve")
    public ResponseEntity<LeaveRequest> approve(@PathVariable Long leaveId, @Valid @RequestBody ApproveLeaveRequest req) {
        var lr = leaveService.approveLeave(leaveId, req.getApprover());
        return ResponseEntity.ok(lr);
    }

    @PostMapping("/leaves/{leaveId}/reject")
    public ResponseEntity<LeaveRequest> reject(@PathVariable Long leaveId, @Valid @RequestBody ApproveLeaveRequest req) {
        var lr = leaveService.rejectLeave(leaveId, req.getApprover());
        return ResponseEntity.ok(lr);
    }

    @GetMapping("/employees/{empId}/leaves")
    public ResponseEntity<List<LeaveRequest>> getLeaves(@PathVariable Long empId) {
        var leaves = leaveService.getLeavesByEmployee(empId);
        return ResponseEntity.ok(leaves);
    }
}
