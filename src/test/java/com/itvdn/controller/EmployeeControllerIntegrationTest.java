package com.itvdn.controller;

import com.itvdn.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;
    private final String ROOT = "/employee";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void addNewEmployee() throws Exception {
        mockMvc.perform(post(ROOT + "/add")
                .param("name", "Oleg")
                .param("position", "CEO")
                .param("phone", "042"))
                .andExpect(status().is3xxRedirection());
    }



    @Test
    public void findEmployeeByNameAndPosition() throws Exception {
        System.out.println(Objects.requireNonNull(mockMvc
                .perform(post(ROOT + "/findByNameAndPosition")
                        .param("name", "Oleg")
                        .param("position", "CEO"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getModelAndView()).getModel().get("employees"));
    }

    @Test
    public void findEmployeeByName() throws Exception {
        System.out.println(Objects.requireNonNull(mockMvc
                .perform(post(ROOT + "/findByName")
                        .param("name", "Oleg"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getModelAndView()).getModel().get("employees"));
    }

    @Test
    public void listAllEmployee() throws Exception {
        System.out.println(Objects.requireNonNull(mockMvc.perform(get(ROOT + "/all"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getModelAndView()).getModel().get("employees"));
    }

    @Test
    public void findEmployeeByNameAndPhone() throws Exception {
        Object employee = Objects.requireNonNull(mockMvc.perform(post(ROOT + "/findByNameAndPhone")
                .param("name", "Oleg")
                .param("phone", "042"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getModelAndView()).getModel().get("employees");

        if (employee instanceof List && !((List) employee).isEmpty()) {
            Optional firstEmployee = ((List) employee).stream().findFirst();
            Employee emp = (Employee) firstEmployee.get();
            System.out.println(emp);
            mockMvc.perform(get(ROOT + "/remove/{id}", emp.getId()))
                    .andExpect(status().is3xxRedirection());
        } else {
            System.out.println("No such employee");
        }
    }
}