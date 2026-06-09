package com.company.leavemanagement.policy;

import com.company.leavemanagement.exception.LeaveException;
import com.company.leavemanagement.model.Employee;
import com.company.leavemanagement.model.LeaveRequest;

public class EarnedLeavePolicy implements LeavePolicy {

    @Override
    public void validateLeave(Employee employee,
                              LeaveRequest request)
            throws LeaveException {

        if (request.getNumberOfDays()
                > employee.calculateMaxAllowedLeave()) {

            throw new LeaveException(
                    "Leave exceeds employee annual limit");
        }
    }
}