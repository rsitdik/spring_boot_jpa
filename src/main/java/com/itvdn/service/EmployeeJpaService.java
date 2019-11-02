package com.itvdn.service;

import com.itvdn.entity.Employee;

import java.util.List;

public interface EmployeeJpaService {

    Employee addEmployee(Employee employee);

    List<Employee> findAll();

    void removeById(long id);

    Employee deleteById(long id);

    Employee getById(long id);

      void listAllEmployee();

    List<Employee> findEmployeeByName(String name);

    List<Employee> findEmployeeByNameAndPosition(String name, String position);

    List<Employee> getEmployeeByNameAndPhone(String name, String phone);
}
