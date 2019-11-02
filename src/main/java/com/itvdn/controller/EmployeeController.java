package com.itvdn.controller;

import com.itvdn.entity.Employee;
import com.itvdn.service.EmployeeJpaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private static final Log LOG = LogFactory.getLog(EmployeeController.class);
    private EmployeeJpaService employeeJpaService;

    public EmployeeController(EmployeeJpaService employeeJpaService) {
        this.employeeJpaService = employeeJpaService;
    }

    @PostMapping(value = "/add")
    public String addNewEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getParameter("name"));
        employee.setPosition(request.getParameter("position"));
        employee.setPhone(request.getParameter("phone"));
//        LOG.info("New employee with id: " + employeeJpaService.addEmployee(employee).getId() + " was added.");
        System.out.println("New employee with id: " + employeeJpaService.addEmployee(employee).getId() + " was added.");
        return "redirect:/employee/all";
    }

    @GetMapping(value = "/all")
    public ModelAndView listAllEmployee(ModelAndView modelAndView) {
        employeeJpaService.listAllEmployee();
        modelAndView.addObject("employees", employeeJpaService.findAll());
        modelAndView.setViewName("employee/employees");
//        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/remove")
    public String deleteEmployeeById(long id, ModelAndView modelAndView) {
        employeeJpaService.removeById(id);
        modelAndView.addObject("employees", employeeJpaService.findAll());
        modelAndView.setViewName("employee/employees");
        return "redirect:/employee/all";
    }

    @PostMapping(value = "/findByName")
    public ModelAndView findEmployeeByName(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeJpaService.findEmployeeByName(
                request.getParameter("name")));
        modelAndView.setViewName("employee/search-results");
        return modelAndView;
    }

    @PostMapping(value = "/findByNameAndPosition")
    public ModelAndView findEmployeeByNameAndPosition(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeJpaService.findEmployeeByNameAndPosition(
                request.getParameter("name"), request.getParameter("position")));
        modelAndView.setViewName("employee/search-results");
        return modelAndView;
    }

    @PostMapping(value = "/findByNameAndPhone")
    public ModelAndView findEmployeeByNameAndPhone(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeJpaService.getEmployeeByNameAndPhone(
                request.getParameter("name"), request.getParameter("phone")));
        modelAndView.setViewName("employee/search-results");
        return modelAndView;
    }

}
