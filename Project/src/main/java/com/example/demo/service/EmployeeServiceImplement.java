package com.example.demo.service;

import com.example.demo.Entity.Employee;
import com.example.demo.dao.EmployeeDAO;
import jakarta.transaction.Transactional;

import java.util.List;

public class EmployeeServiceImplement implements EmployeeService {

    // 1. define field for EmployeeDAO
    private EmployeeDAO employeeDAO;



    // 2. inject EmployeeDAO using constructor injection
    public EmployeeServiceImplement (EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }



    // 3. Override the methods and in return call the methods from employeeDAO
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}
