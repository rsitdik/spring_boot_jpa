package com.itvdn.controller;

import com.itvdn.service.impl.Man;
import com.itvdn.service.impl.Woman;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppControllerTest {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    // создание имитации вэб-окружения
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void testContext() {
        assertNotNull(wac.getBean("beanForTest"));
    }

    @Test
    public void testController1() throws Exception {
        mockMvc.perform(get("/bye2"))
                .andDo(print())
                .andExpect(view().name("bye"));
    }

    @Test
    public void testController2() throws Exception {
        mockMvc.perform(get("/bye2"))
                .andExpect(status().isOk())
                .andExpect(view().name("bye"));
    }

    @Test
    public void testController3() throws Exception {
        mockMvc.perform(get("/path/{userName}", "Clark"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("greeting"));
    }

    @Test
    public void testController4() throws Exception {
        mockMvc.perform(get("/rest/{name}", "Oleg"))
                .andExpect(status().is2xxSuccessful());
        assertTrue(mockMvc.perform(get("/rest/{name}", "Oleg"))
                .andReturn().getResponse().getContentAsString().contains("Oleg"));
    }

    @Test
    public void testController5() throws Exception {
        mockMvc.perform(post("/pass-data")
                .flashAttr("user", new Man("Vasiliy", "Rogov", 38)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("summary"))
                .andDo(print());
    }

    @Test
    public void testController6() throws Exception {
        mockMvc.perform(post("/pass-data")
                .param("name", "Veronika")
                .param("surname", "Aksenova")
                .param("years", "33")
                .flashAttr("user", new Woman()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(forwardedUrl("summary"))
                .andExpect(view().name("summary"))
                .andDo(print());
    }

    @Test
    public void testController7() throws Exception {
        mockMvc.perform(get("/hello2")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testController8() throws Exception {
        mockMvc.perform(get("/employee/all")).andExpect(status().is2xxSuccessful());
    }
}
