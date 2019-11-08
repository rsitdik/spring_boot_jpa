package com.itvdn.controller;

import com.itvdn.entity.Employee;
import com.itvdn.service.EmployeeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private static final Log LOG = LogFactory.getLog(EmployeeController.class);
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add")
    public String addNewEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getParameter("name"));
        employee.setPosition(request.getParameter("position"));
        employee.setPhone(request.getParameter("phone"));
        LOG.info("New employee with id: " + employeeService.addEmployee(employee).getId() + " was added.");
//        System.out.println("New employee with id: " + employeeService.addEmployee(employee).getId() + " was added.");
        return "redirect:/employee/all";
    }

    @GetMapping(value = "/all")
    public ModelAndView listAllEmployee(ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeService.findAll());
        modelAndView.setViewName("employee/employees");
        return modelAndView;
    }

    @GetMapping(value = "/remove/{id}")
    public String deleteEmployeeById(@PathVariable long id, ModelAndView modelAndView) {
        employeeService.removeById(id);
        modelAndView.addObject("employees", employeeService.findAll());
        modelAndView.setViewName("employee/employees");
        return "redirect:/employee/all";
    }

    @GetMapping(value = "/remove")
    public String deleteEmployee(long id, ModelAndView modelAndView) {
        employeeService.removeById(id);
        modelAndView.addObject("employees", employeeService.findAll());
        modelAndView.setViewName("employee/employees");
        return "redirect:/employee/all";
    }

    @PostMapping(value = "/findByName")
    public ModelAndView findEmployeeByName(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeService.findEmployeeByName(
                request.getParameter("name")));
        modelAndView.setViewName("employee/search-results");
        return modelAndView;
    }

    @PostMapping(value = "/findByNameAndPosition")
    public ModelAndView findEmployeeByNameAndPosition(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeService.findEmployeeByNameAndPosition(
                request.getParameter("name"), request.getParameter("position")));
        modelAndView.setViewName("employee/search-results");
        return modelAndView;
    }

    @PostMapping(value = "/findByNameAndPhone")
    public ModelAndView findEmployeeByNameAndPhone(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeService.getEmployeeByNameAndPhone(
                request.getParameter("name"), request.getParameter("phone")));
        modelAndView.setViewName("employee/search-results");
        return modelAndView;
    }

    @GetMapping(value = "/unsupported")
    public String unsupportedMethod() {
        employeeService.throwException();
        return "/employee/all";
    }
}
