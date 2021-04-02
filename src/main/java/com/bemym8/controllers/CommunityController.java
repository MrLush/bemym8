package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
    
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/community")
    public String projectsPage(Model model){
        Iterable<Project> project = projectRepository.findAllByOrderByIdAsc();
        model.addAttribute("project", project);
        model.addAttribute("title","BeMyM8 - Community");
        return "community";
    }
}
