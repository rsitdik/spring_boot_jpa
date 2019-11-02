package com.itvdn.controller;

import com.itvdn.entity.Employee;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/employee";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String GET_ALL = "/all";
    private static final String GET_BY_ID = GET + "/{id}";


    @Test
    public void checkAddNewEmployee() {
        Employee employee = createEmployee();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Employee> responseEntity = template.exchange(
                ROOT + GET_BY_ID,
                HttpMethod.GET,
                null,
                Employee.class,
                employee.getId()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Employee receivedEmployee = responseEntity.getBody();
        assertNotNull(receivedEmployee);
    }

    private Employee createEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee();
        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
        RestTemplate template = new RestTemplate();
        Employee createdEmployee = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Employee.class
        ).getBody();

        assertNotNull(createdEmployee);
        assertEquals("Ivan", createdEmployee.getName());
        return createdEmployee;
    }

    private Employee prefillEmployee() {
        Employee employee = new Employee();
        employee.setName("Ivan");
        employee.setPosition("bogatyr");
        employee.setPhone("00000000");

//        Employee employee1 = new Employee();
//        employee1.setName("Janna");
//        employee1.setPosition("doctor");
//        employee1.setPhone("353535742");
//
//        List<Employee> list = new ArrayList<>();
//        list.add(employee);
//        list.add(employee1);
//
//        employee.setEmployees(list);
        return employee;
    }
}