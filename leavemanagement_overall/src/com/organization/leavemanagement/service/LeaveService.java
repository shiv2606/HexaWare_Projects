package com.organization.leavemanagement.service;

import com.organization.leavemanagement.exception.EmployeeNotFoundException;
import com.organization.leavemanagement.exception.InsufficientLeaveBalanceException;
import com.organization.leavemanagement.exception.InvalidLeaveRequestException;
import com.organization.leavemanagement.model.Employee;
import com.organization.leavemanagement.model.LeaveRequest;
import com.organization.leavemanagement.model.LeaveType;
import com.organization.leavemanagement.repository.EmployeeRepository;
import com.organization.leavemanagement.util.ValidationUtil;

public class LeaveService {
    private EmployeeRepository repository;

    public LeaveService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void applyLeave(LeaveRequest request)
            throws InvalidLeaveRequestException,
            EmployeeNotFoundException,
            InsufficientLeaveBalanceException {

        ValidationUtil.validateString(
                request.getEmployeeId(), "Employee ID");

        ValidationUtil.validateString(
                request.getReason(), "Reason");

        ValidationUtil.validateNumber(
                request.getNumberOfDays());

        ValidationUtil.validateEnum(
                request.getLeaveType());

        Employee emp =
                repository.getEmployee(request.getEmployeeId());

        if (emp == null) {
            throw new EmployeeNotFoundException(
                    "Employee not found");
        }

        if (request.getLeaveType() == LeaveType.SICK
                && request.getNumberOfDays() > 5) {
            throw new InvalidLeaveRequestException(
                    "Maximum 5 consecutive sick leaves allowed");
        }

        if (request.getNumberOfDays()
                > emp.getLeaveBalance()) {
            throw new InsufficientLeaveBalanceException(
                    "Insufficient leave balance");
        }

        if (emp.getTotalLeaveUsed()
                + request.getNumberOfDays() > 20) {
            throw new InvalidLeaveRequestException(
                    "Annual leave limit exceeded (20)");
        }

        emp.setLeaveBalance(
                emp.getLeaveBalance()
                        - request.getNumberOfDays());

        emp.setTotalLeaveUsed(
                request.getNumberOfDays());
    }
}

