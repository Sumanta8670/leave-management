package com.symplora.leavemanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class ApproveLeaveRequest {
    @NotBlank
    private String approver;

    public String getApprover() { return approver; }
    public void setApprover(String approver) { this.approver = approver; }
}
