package com.company.leavemanagement.policy;

import com.company.leavemanagement.exception.LeaveException;
import com.company.leavemanagement.model.Employee;
import com.company.leavemanagement.model.LeaveRequest;

public class CasualLeavePolicy implements LeavePolicy {

    @Override
    public void validateLeave(Employee employee,
                              LeaveRequest request)
            throws LeaveException {

        if (request.getNumberOfDays() > 5) {
            throw new LeaveException(
                    "Casual Leave cannot exceed 5 days");
        }
    }
}