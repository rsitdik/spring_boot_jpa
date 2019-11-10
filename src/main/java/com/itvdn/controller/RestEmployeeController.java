package com.itvdn.controller;

import com.itvdn.entity.Employee;
import com.itvdn.service.EmployeeService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

// @ResponseBody по умоляанию для всех методов @RestController
@RestController
@RequestMapping("/rest/employee")
public class RestEmployeeController {
    private EmployeeService employeeService;
    private CacheManager cacheManager;

    public RestEmployeeController(EmployeeService employeeService, CacheManager cacheManager) {
        this.employeeService = employeeService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/add")
    public Employee addEmployeePOST(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeInfo(@PathVariable long id) throws InterruptedException {
        Cache.ValueWrapper empl = Objects.requireNonNull(cacheManager.getCache("empl")).get(id);

        return empl != null ? (Employee) empl.get() : employeeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No employee with such id!"));
//        return employeeService.findById(id).get();
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeeInfo() throws InterruptedException {
        return employeeService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public Employee delEmployee(@PathVariable long id) {
        return employeeService.deleteById(id);
    }
}
