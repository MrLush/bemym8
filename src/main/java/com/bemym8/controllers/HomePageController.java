package com.bemym8.controllers;

import com.bemym8.models.User;
import com.bemym8.repo.ProjectRepository;
import com.bemym8.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String homePage(Model model,@AuthenticationPrincipal UserDetails currentUser){
        User user = (User) userRepository.findByUsername(currentUser.getUsername());
        model.addAttribute("currentStudent", user);
        model.addAttribute("title","BeMyM8 - Home page");
        return "home";
    }
/*
    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("title","BeMyM8 - Login");
        return "login";
    }
*/
}
