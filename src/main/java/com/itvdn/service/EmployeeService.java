package com.itvdn.service;

import com.itvdn.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    List<Employee> findAll() throws InterruptedException;

    void removeById(long id);

    Employee deleteById(long id);

    Employee getById(long id);

    Employee findByName(String name);

    List<Employee> findEmployeeByName(String name);

    List<Employee> findEmployeeByNameAndPosition(String name, String position);

    List<Employee> getEmployeeByNameAndPhone(String name, String phone);

    void throwException();

    void clearCache();

    Optional<Employee> findById(long id) throws InterruptedException;
}
