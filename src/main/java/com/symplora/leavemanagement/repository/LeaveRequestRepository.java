package com.symplora.leavemanagement.repository;

import com.symplora.leavemanagement.model.Employee;
import com.symplora.leavemanagement.model.LeaveRequest;
import com.symplora.leavemanagement.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT l FROM LeaveRequest l WHERE l.employee.id = :empId AND l.status IN :states "
            + "AND l.startDate <= :endDate AND l.endDate >= :startDate")
    List<LeaveRequest> findOverlapping(@Param("empId") Long empId,
                                       @Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate,
                                       @Param("states") List<LeaveStatus> states);

    List<LeaveRequest> findByEmployeeId(Long empId);

    List<LeaveRequest> findByEmployee(Employee employee);
}
