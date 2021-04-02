package com.bemym8.controllers;

import com.bemym8.models.Project;
import com.bemym8.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Iterator;

@Controller
public class SupportController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/support")
    public String projectsPage(Model model){
        model.addAttribute("title","BeMyM8 - Support");

        // Here is id's of post for page support
        Iterable<Long> ids = Arrays.asList(11L, 12L, 13L);
        Iterable<Project> project = projectRepository.findByOrderByIdAsc(ids);
        model.addAttribute("project", project);
        return "support";
    }
}
