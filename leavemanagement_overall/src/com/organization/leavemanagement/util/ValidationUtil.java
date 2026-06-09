package com.organization.leavemanagement.util;

import com.organization.leavemanagement.exception.InvalidLeaveRequestException;
import com.organization.leavemanagement.model.LeaveType;

public class ValidationUtil {
    public static void validateString(String value, String fieldName)
            throws InvalidLeaveRequestException {

        if (value == null || value.trim().isEmpty()) {
            throw new InvalidLeaveRequestException(fieldName + " cannot be blank");
        }
    }

    public static void validateNumber(int value)
            throws InvalidLeaveRequestException {

        if (value <= 0) {
            throw new InvalidLeaveRequestException(
                    "Leave days must be greater than 0");
        }
    }

    public static void validateEnum(LeaveType type)
            throws InvalidLeaveRequestException {

        if (type == null) {
            throw new InvalidLeaveRequestException(
                    "Invalid Leave Type");
        }
    }

}
