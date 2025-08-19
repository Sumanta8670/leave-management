package com.symplora.leavemanagement.dto;

import com.symplora.leavemanagement.model.LeaveType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ApplyLeaveRequest {
    @NotNull private LocalDate startDate;
    @NotNull private LocalDate endDate;
    @NotNull private LeaveType type;
    private String notes;

    // getters/setters
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public LeaveType getType() { return type; }
    public void setType(LeaveType type) { this.type = type; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
