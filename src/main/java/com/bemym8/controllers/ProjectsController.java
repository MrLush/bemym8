package com.bemym8.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectsController {
    @GetMapping("/projects")
    public String projectsPage(Model model){
        model.addAttribute("title","BeMyM8 - Projects");
        return "projects";
    }
}
