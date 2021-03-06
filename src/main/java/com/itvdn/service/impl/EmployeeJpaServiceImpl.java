package com.itvdn.service.impl;

import com.google.common.collect.Lists;
import com.itvdn.entity.Employee;
import com.itvdn.jpa.EmployeeRepository;
import com.itvdn.service.EmployeeJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeJpaServiceImpl implements EmployeeJpaService {
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return Lists.newArrayList(employeeRepository.findAll());
    }

    @Override
    public void removeById(long id) {
        employeeRepository.deleteById(id);
    }

    // ? или
    @Override
    public Employee deleteById(long id) {
        Employee employeeForDelete = getById(id);
        employeeRepository.delete(employeeForDelete);
        return employeeForDelete;
    }

    @Override
    public Employee getById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void listAllEmployee() {
        employeeRepository.findAll().forEach(e -> System.out.println(e.getId()));
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public List<Employee> findEmployeeByNameAndPosition(String name, String position) {
        return employeeRepository.findEmployeeByNameAndPosition(name, position);
    }

    @Override
    public List<Employee> getEmployeeByNameAndPhone(String name, String phone) {
        return employeeRepository.findEmployeeByNameAndPhone(name, phone);
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
