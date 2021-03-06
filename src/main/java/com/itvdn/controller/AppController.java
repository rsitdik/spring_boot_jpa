package com.itvdn.controller;

import com.itvdn.service.User;
import com.itvdn.service.impl.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AppController {
    private User user;

    @GetMapping("/user")
    public String helloMan(Model model) {
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("years", user.getYears());
        return "user";
    }

    @RequestMapping("/hello")
    public String helloPage(Model model) {
        model.addAttribute("msg", "Spring Boot");
        return "hello";
    }

    @GetMapping(value = "/bye")
    public String bye() {
        return "bye";
    }

    @GetMapping(value = "/pass-data")
    public String passDataFromUser() {
        return "pass-data";
    }

    @PostMapping(value = "/pass-data")
    public ModelAndView passDataFromUser(@ModelAttribute("user") Man user, ModelAndView modelAndView) {
        modelAndView.setViewName("summary");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/path/{userName}")
    public ModelAndView greeting(@PathVariable String userName, ModelAndView modelAndView) {
        modelAndView.setViewName("greeting");
        modelAndView.addObject("userName", userName);
        return modelAndView;
    }

    @Autowired
    @Qualifier(value = "man")
    public void setUser(User user) {
        this.user = user;
    }

}
