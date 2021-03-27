package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProjectsController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public String projectsPage(Model model){
        model.addAttribute("title","BeMyM8 - Projects");
        Iterable<Project> project = projectRepository.findAllByOrderByIdAsc();
        model.addAttribute("project", project);
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String projectsPage(@PathVariable(value = "id") long id, Model model){
        if (!projectRepository.existsById(id)){
            System.out.println("Error: trying to access non-existent project");
            return "redirect:/projects";
        }
        Project project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        model.addAttribute("project", project);
        return "projects-details";
    }
}
