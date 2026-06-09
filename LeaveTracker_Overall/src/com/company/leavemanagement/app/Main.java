package com.company.leavemanagement.app;

import com.company.leavemanagement.exception.LeaveException;
import com.company.leavemanagement.model.Employee;
import com.company.leavemanagement.model.LeaveRequest;
import com.company.leavemanagement.model.PermanentEmployee;
import com.company.leavemanagement.policy.CasualLeavePolicy;
import com.company.leavemanagement.policy.LeavePolicy;
import com.company.leavemanagement.repository.LeaveRepository;
import com.company.leavemanagement.service.LeaveService;

public class Main {

    public static void main(String[] args) {

        Employee employee =
                new PermanentEmployee(
                        "EMP101",
                        "John",
                        15);

        LeaveRequest request =
                new LeaveRequest(
                        "LR001",
                        employee,
                        "CASUAL",
                        3);

        LeavePolicy policy =
                new CasualLeavePolicy();

        LeaveService service =
                new LeaveService(policy);

        LeaveRepository repository =
                new LeaveRepository();

        try {

            service.processLeaveRequest(
                    employee,
                    request);

            repository.addLeaveRequest(request);

            System.out.println(
                    "Leave Approved");

            System.out.println(
                    "Status : "
                            + request.getStatus());

            System.out.println(
                    "Remaining Balance : "
                            + employee.getLeaveBalance());

        } catch (LeaveException e) {

            System.out.println(
                    e.getMessage());

            System.out.println(
                    "Status : "
                            + request.getStatus());
        }
    }
}