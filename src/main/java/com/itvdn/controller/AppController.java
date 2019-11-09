package com.itvdn.controller;

import com.itvdn.model.Authorization;
import com.itvdn.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AppController {
    private User user;
    private Authorization auth;

    @GetMapping("/user")
    public String helloMan(Model model) {
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("years", user.getYears());
        return "user";
    }

    @GetMapping("/hello")
    public String helloPage(Model model) {
        model.addAttribute("msg", "Spring Boot");
        return "hello";
    }

    @GetMapping(value = "/bye")
    public String bye() {
        return "bye";
    }

    @GetMapping(value = "/bye2")
    public ModelAndView bye(ModelAndView modelAndView) {
        modelAndView.setViewName("bye");
        return modelAndView;
    }

    @GetMapping(value = "/pass-data")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String passDataFromUser() {
        return "pass-data";
    }

    @PostMapping(value = "/pass-data")
    public ModelAndView passDataFromUser(@ModelAttribute("user") User user, ModelAndView modelAndView) {
        System.out.println(user);
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

    @GetMapping(value = "/rest/{name}")
    @ResponseBody
    public String retRest(@PathVariable String name) {
        return name + Math.random() * 1000;
    }


    @GetMapping(value = "/authorize-me")
    public ModelAndView authorize(ModelAndView modelAndView) {
        auth.setAuthorized(Boolean.TRUE);
        modelAndView.setViewName("authorized");
        modelAndView.addObject("authorized", auth);
        return modelAndView;
    }

    @GetMapping(value = "/unauthorize-me")
    public ModelAndView unAuthorize(ModelAndView modelAndView) {
        auth.setAuthorized(Boolean.FALSE);
        modelAndView.setViewName("bye");
        modelAndView.addObject("authorized", auth);
        return modelAndView;
    }

    @RequestMapping("/password/{password}")
    public String getAdminInfo(@PathVariable("password") String password, Model model) {
        model.addAttribute("password", password);
        model.addAttribute("passwordAfterEncode", new BCryptPasswordEncoder().encode(password));
        return "password";
    }

    @Autowired
    @Qualifier(value = "man")
    public void setUser(User user) {
        this.user = user;
    }

    @Autowired
    public void setAuth(Authorization auth) {
        this.auth = auth;
    }
}
