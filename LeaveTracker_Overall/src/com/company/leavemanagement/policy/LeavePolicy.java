package com.company.leavemanagement.policy;

import com.company.leavemanagement.exception.LeaveException;
import com.company.leavemanagement.model.Employee;
import com.company.leavemanagement.model.LeaveRequest;

public interface LeavePolicy {

    void validateLeave(Employee employee,
                       LeaveRequest request)
            throws LeaveException;
}