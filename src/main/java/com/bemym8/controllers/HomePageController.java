package com.bemym8.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("title","BeMyM8 - Home page");
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("title","BeMyM8 - Login");
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("title","BeMyM8 - Registration");
        return "registration";
    }
}
