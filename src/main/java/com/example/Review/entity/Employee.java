package com.example.Review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "Reviewer_ID", nullable = false)
    private int reviewerId;
    @Column(name = "Employee_DFK", nullable = false)
    private int employeeDFK;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getEmployeeDFK() {
        return employeeDFK;
    }

    public void setEmployeeDFK(int employeeDFK) {
        this.employeeDFK = employeeDFK;
    }
}
