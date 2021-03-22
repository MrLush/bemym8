package com.bemym8.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
    @GetMapping("/community")
    public String projectsPage(Model model){
        model.addAttribute("title","BeMyM8 - Community");
        return "community";
    }
}
