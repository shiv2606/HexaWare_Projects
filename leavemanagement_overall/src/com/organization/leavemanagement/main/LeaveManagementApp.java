package com.organization.leavemanagement.main;

import com.organization.leavemanagement.exception.EmployeeNotFoundException;
import com.organization.leavemanagement.exception.InsufficientLeaveBalanceException;
import com.organization.leavemanagement.exception.InvalidLeaveRequestException;
import com.organization.leavemanagement.model.Employee;
import com.organization.leavemanagement.model.LeaveRequest;
import com.organization.leavemanagement.model.LeaveType;
import com.organization.leavemanagement.repository.EmployeeRepository;
import com.organization.leavemanagement.service.LeaveService;

import java.time.LocalDate;

public class LeaveManagementApp {
    public static void main(String[] args) {

        EmployeeRepository repo =
                new EmployeeRepository();

        repo.addEmployee(
                new Employee("E101",
                        "Anu", 1));
        repo.addEmployee(new Employee("E102","Anu",30));

        LeaveService service =
                new LeaveService(repo);

        LeaveRequest request =
                new LeaveRequest(
                        "E102",
                        LeaveType.CASUAL,
                        22,
                        "Fever",
                        LocalDate.now()
                );

        try {
            service.applyLeave(request);
            System.out.println(
                    "Leave Approved Successfully");

        } catch (InvalidLeaveRequestException e) {
            System.out.println(
                    "Validation Error: "
                            + e.getMessage());

        } catch (EmployeeNotFoundException e) {
            System.out.println(
                    "Employee Error: "
                            + e.getMessage());

        } catch (InsufficientLeaveBalanceException e) {
            System.out.println(
                    "Balance Error: "
                            + e.getMessage());
        }
        }
}
