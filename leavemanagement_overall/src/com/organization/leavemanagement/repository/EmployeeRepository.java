package com.organization.leavemanagement.repository;

import com.organization.leavemanagement.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {
    private Map<String, Employee> employeeMap = new HashMap<>();

    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
    }

    public Employee getEmployee(String employeeId) {
        return employeeMap.get(employeeId);
    }
}

