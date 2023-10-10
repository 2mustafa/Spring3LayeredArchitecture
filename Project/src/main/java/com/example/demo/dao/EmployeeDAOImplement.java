package com.example.demo.dao;

import com.example.demo.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeeDAOImplement implements EmployeeDAO{

    // 1. define field for EntityManager
    private EntityManager entityManager;


    // 2. After creating the DAOImplement class, we need to inject the EntityManager using constructor injection
    @Autowired
    public EmployeeDAOImplement(EntityManager myEntityManager) {
        this.entityManager = myEntityManager;
    }


    // 3. Override the methods
    @Override
    public List<Employee> findAll() {
        // 1. create a query
        TypedQuery<Employee> typedQuery = entityManager.createQuery("from Employee", Employee.class);

        // 2. execute query and get result list
        List<Employee> employees = typedQuery.getResultList();

        // 3. return the result
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee savedEmployee = entityManager.merge(employee);
        return savedEmployee;

    }

    @Override
    public void deleteById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);

    }
}
