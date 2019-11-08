package com.itvdn.controller;

import com.itvdn.entity.Employee;
import com.itvdn.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @ResponseBody по умоляанию для всех методов @RestController
@RestController
@RequestMapping("/rest/employee")
public class RestEmployeeController {
    private EmployeeService employeeService;

    public RestEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Employee addEmployeePOST(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeInfo(@PathVariable long id) {
        return employeeService.getById(id);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeeInfo(){
        return employeeService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public Employee delEmployee(@PathVariable long id){
        return employeeService.deleteById(id);
    }
}
