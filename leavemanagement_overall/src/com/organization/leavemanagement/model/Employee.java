package com.organization.leavemanagement.model;

public class Employee {
    private String employeeId;
    private String name;
    private int leaveBalance;
    public int totalLeaveUsed;

    public Employee(String employeeId, String name, int leaveBalance) {
        this.employeeId = employeeId;
        this.name = name;
        this.leaveBalance = leaveBalance;
        this.totalLeaveUsed = 0;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public int getTotalLeaveUsed() {
        return totalLeaveUsed;
    }

    public void setTotalLeaveUsed(int daysUsed) {
        this.totalLeaveUsed += daysUsed;
    }
}
