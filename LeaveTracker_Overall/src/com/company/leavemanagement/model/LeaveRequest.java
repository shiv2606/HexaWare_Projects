package com.company.leavemanagement.model;

public class LeaveRequest {

    private String requestId;
    private Employee employee;
    private String leaveType;
    private int numberOfDays;
    private String status;

    public LeaveRequest(String requestId,
                        Employee employee,
                        String leaveType,
                        int numberOfDays) {

        this.requestId = requestId;
        this.employee = employee;
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
        this.status = "PENDING";
    }

    public String getRequestId() {
        return requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}