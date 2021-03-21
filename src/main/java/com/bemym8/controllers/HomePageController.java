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
    @GetMapping("/carousel.css")
    public String carouselCss(){
        return "css/carousel.css";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("title","Login");
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("title","Registration");
        return "registration";
    }
}
