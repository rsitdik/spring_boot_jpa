package com.itvdn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }
}
