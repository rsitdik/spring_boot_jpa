package com.itvdn.service.impl;

import com.google.common.collect.Lists;
import com.itvdn.entity.Employee;
import com.itvdn.jpa.EmployeeRepository;
import com.itvdn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeServiceImpl employeeService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Cacheable("employees")
    @Override
    public List<Employee> findAll() throws InterruptedException {
//        System.out.println("Star sleeping for 3 seconds");
//        Thread.sleep(3000);
//        System.out.println("End sleeping for 3 seconds");
        return Lists.newArrayList(employeeRepository.findAll());
    }

    @CacheEvict("employees")
    @Override
    public void clearCache() {
        System.out.println("employees cache cleared");
    }

    @CacheEvict(value = "employees", allEntries = true)
    @Override
    public void removeById(long id) {
        employeeRepository.deleteById(id);
    }

    // for rest
    @Override
    public Employee deleteById(long id) {
//        Employee employeeForDelete = getById(id);
        Employee employeeForDelete = getById(id);
        employeeRepository.delete(employeeForDelete);
        return employeeForDelete;
    }

    @Override
    public Employee getById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee findByName(String name) {
        return employeeRepository.findByName(name);
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

    public void throwException() {
        throw new UnsupportedOperationException("Not support yet.");
    }

    /**
     * The difference between @Cacheable and @CachePut is that @Cacheable will skip running the method,
     * whereas @CachePut will actually run the method and then put its results in the cache.
     *
     * @param id
     * @return
     */
    @Override
    @CachePut(value = "empl", condition = "#result != null", key = "#result.id")
    public Optional<Employee> findById(long id) throws InterruptedException {
        employeeService.findAll(); // call with self inject
        System.out.println("Getting employee from repo");
        return Optional.ofNullable(employeeRepository.findById(id));
    }

    // self inject
    @Autowired
    public void setEmployeeService(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
}
