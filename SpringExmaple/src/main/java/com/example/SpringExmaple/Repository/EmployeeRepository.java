package com.example.SpringExmaple.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringExmaple.DTO.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}