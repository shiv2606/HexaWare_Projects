package com.company.leavemanagement.service;

import com.company.leavemanagement.exception.LeaveException;
import com.company.leavemanagement.model.Employee;
import com.company.leavemanagement.model.LeaveRequest;
import com.company.leavemanagement.policy.LeavePolicy;

public class LeaveService {

    private LeavePolicy leavePolicy;

    public LeaveService(LeavePolicy leavePolicy) {
        this.leavePolicy = leavePolicy;
    }

    public void processLeaveRequest(
            Employee employee,
            LeaveRequest request)
            throws LeaveException {

        leavePolicy.validateLeave(
                employee,
                request);

        if (employee.getLeaveBalance()
                < request.getNumberOfDays()) {

            request.setStatus("REJECTED");

            throw new LeaveException(
                    "Insufficient leave balance");
        }

        employee.setLeaveBalance(
                employee.getLeaveBalance()
                        - request.getNumberOfDays());

        request.setStatus("APPROVED");
    }
}